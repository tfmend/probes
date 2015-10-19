package com.thiagofurtado.cli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.type.TypeReference;

import com.google.gson.Gson;
import com.thiagofurtado.api.Direction;
import com.thiagofurtado.api.ExplorationResult;
import com.thiagofurtado.api.ExplorationSpec;
import com.thiagofurtado.api.Position;
import com.thiagofurtado.api.ProbeExplorationSpec;

/**
 * @author thiago
 */
public class CliApplication {

	private String inputFilePath;
	private String serverUrl;
	private Options options;

	public static void main(String[] args) {
		new CliApplication().execute(args);
	}

	public void execute(String[] args) {
		this.createOptions();
		this.parseOptions(args);
		this.explorate();
	}

	/**
	 * @throws IOException
	 * @throws JsonParseException
	 *
	 */
	private void sendExplorationRequest(ExplorationSpec exploration, String url)
			throws JsonParseException, IOException {

		CloseableHttpClient client = HttpClients.createDefault();
		Gson gson = new Gson();
		try {
			String json = gson.toJson(exploration);

			HttpPost postRequest = new HttpPost(url + "/explore");
			postRequest.setHeader("content-type", "application/json;charset=utf-8");
			postRequest.setEntity(new StringEntity(json, "UTF-8"));
			CloseableHttpResponse response = client.execute(postRequest);
			try {

				if (response.getStatusLine().getStatusCode() == 200) {
					MappingJsonFactory factory = new MappingJsonFactory();
					JsonParser parser = factory.createJsonParser(response.getEntity().getContent());
					List<ExplorationResult> output = parser.readValueAs(new TypeReference<List<ExplorationResult>>() {
						// nothing specific to do here
					});

					for (ExplorationResult expRs : output) {
						System.out.println(expRs);
					}
				} else {
					System.out.println(response.getStatusLine());
				}

			} catch (Exception e) {
				System.out.println("Error reading server response");
			} finally {
				response.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				System.out.println("Error closing connection");
			}
		}
	}

	/**
	 * @param file
	 * @return
	 */
	private ExplorationSpec buildExplorationSpecFromFile(File file) {

		ExplorationSpec spec = new ExplorationSpec();
		List<ProbeExplorationSpec> specs = new ArrayList<>();
		spec.setProbePaths(specs);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));

			boolean readPlateau = false;
			boolean alternate = false;
			Position position = null;
			Direction direction = null;
			for (String line; (line = br.readLine()) != null;) {
				if (!readPlateau) {
					String[] split = line.split(" ");
					int x = Integer.valueOf(split[0]);
					int y = Integer.valueOf(split[1]);
					spec.setPlateauXBound(x);
					spec.setPlateauYBound(y);
					readPlateau = true;
				} else {
					if (!alternate) {
						String[] split = line.split(" ");
						int initialX = Integer.valueOf(split[0]);
						int initialY = Integer.valueOf(split[1]);
						String initialDirection = split[2];
						position = new Position(initialX, initialY);
						direction = this.getDirection(initialDirection);
					} else {
						specs.add(new ProbeExplorationSpec(line, position, direction));
					}
					alternate = !alternate;
				}
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);

		} catch (IOException e) {
			throw new RuntimeException(e);

		}
		return spec;
	}

	/**
	 * @param initialDirection
	 * @return
	 */
	private Direction getDirection(String initialDirection) {
		switch (initialDirection) {
		case "N":
			return Direction.NORTH;
		case "S":
			return Direction.SOUTH;
		case "E":
			return Direction.EAST;
		case "W":
			return Direction.WEST;

		default:
			throw new IllegalArgumentException();
		}
	}

	private void explorate() {
		File file = new File(this.inputFilePath);
		ExplorationSpec explorationSpec = this.buildExplorationSpecFromFile(file);
		try {
			this.sendExplorationRequest(explorationSpec, this.serverUrl);
		} catch (JsonParseException e) {
			throw new RuntimeException(e);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void parseOptions(String[] args) {
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(this.options, args);
		} catch (ParseException e) {
			System.out.println("Impossible to parse args");
			System.exit(1);
		}

		if (cmd.hasOption("f") && cmd.hasOption("u")) {
			this.inputFilePath = cmd.getOptionValue("f");
			this.serverUrl = cmd.getOptionValue("u");
		} else {
			HelpFormatter formater = new HelpFormatter();
			formater.printHelp("CliApplication", this.options);
			System.exit(0);
		}
	}

	private void createOptions() {
		this.options = new Options();
		this.options.addOption("f", "file", true, "input file");
		this.options.addOption("u", "url", true, "service URL");
	}
}
