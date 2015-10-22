package com.thiagofurtado.cli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thiagofurtado.api.Direction;
import com.thiagofurtado.api.ExplorationSpec;
import com.thiagofurtado.api.Position;
import com.thiagofurtado.api.ProbeExplorationSpec;

public class ExplorationSpecBuilder {

	/**
	 * @param file
	 * @return
	 */
	public ExplorationSpec buildExplorationSpecFromFile(File file) {

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
					try {
						String[] split = line.split(" ");
						int x = Integer.valueOf(split[0]);
						int y = Integer.valueOf(split[1]);
						spec.setPlateauXBound(x);
						spec.setPlateauYBound(y);
						readPlateau = true;
					} catch (Exception e) {
						throw new IllegalArgumentException();
					}
				} else {
					if (!alternate) {
						try{
							String[] split = line.split(" ");
							int initialX = Integer.valueOf(split[0]);
							int initialY = Integer.valueOf(split[1]);
							String initialDirection = split[2];
							position = new Position(initialX, initialY);
							direction = this.getDirection(initialDirection);
						} catch (Exception e) {
							throw new IllegalArgumentException();
						}
					} else {
						specs.add(new ProbeExplorationSpec(line, position, direction));
					}
					alternate = !alternate;
				}
			}
			if (!readPlateau || alternate) {
				throw new IllegalArgumentException();
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Input file syntax error");
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
}