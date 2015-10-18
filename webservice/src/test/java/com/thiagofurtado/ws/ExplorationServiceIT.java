package com.thiagofurtado.ws;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thiagofurtado.api.Direction;
import com.thiagofurtado.api.ExplorationResult;
import com.thiagofurtado.api.ExplorationSpec;
import com.thiagofurtado.api.Position;
import com.thiagofurtado.api.ProbeExplorationSpec;

public class ExplorationServiceIT {
	private static String endpointUrl;

	@BeforeClass
	public static void beforeClass() {
		endpointUrl = System.getProperty("service.url");
	}

	@Test
	public void testAvailability() throws Exception {
		List<Object> providers = new ArrayList<Object>();
		providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());

		ExplorationSpec exploration = new ExplorationSpec();

		WebClient client = WebClient.create(endpointUrl + "/explore", providers);
		Response response = client.accept("application/json").type("application/json").post(exploration);

		Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	public void testREST() throws Exception {
		List<Object> providers = new ArrayList<Object>();
		providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());

		ExplorationSpec exploration = new ExplorationSpec();
		exploration.setPlateauXBound(5);
		exploration.setPlateauYBound(5);

		List<ProbeExplorationSpec> probePaths = new ArrayList<>();
		probePaths.add(new ProbeExplorationSpec("LMLMLMLMM", new Position(1, 2), Direction.NORTH));
		probePaths.add(new ProbeExplorationSpec("MMRMMRMRRM", new Position(3, 3), Direction.EAST));
		exploration.setProbePaths(probePaths);

		WebClient client = WebClient.create(endpointUrl + "/explore", providers);
		Response response = client.accept("application/json").type("application/json").post(exploration);

		Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

		MappingJsonFactory factory = new MappingJsonFactory();
		JsonParser parser = factory.createJsonParser((InputStream) response.getEntity());
		List<ExplorationResult> output = parser.readValueAs(new TypeReference<List<ExplorationResult>>() {
		});

		Assert.assertNotNull(output);
		Assert.assertTrue(output.size() == 2);
		for (ExplorationResult result : output) {
			Assert.assertNotNull(result);
			Assert.assertTrue((result.getFinalDirection().equals(Direction.NORTH)
					&& result.getFinalPosition().equals(new Position(1, 3)))
					|| (result.getFinalDirection().equals(Direction.EAST)
							&& result.getFinalPosition().equals(new Position(5, 1))));
		}
	}

	@Test
	public void testRESTNoProbes() throws Exception {
		List<Object> providers = new ArrayList<Object>();
		providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());

		ExplorationSpec exploration = new ExplorationSpec();
		exploration.setPlateauXBound(5);
		exploration.setPlateauYBound(5);

		List<ProbeExplorationSpec> probePaths = new ArrayList<>();
		exploration.setProbePaths(probePaths);

		WebClient client = WebClient.create(endpointUrl + "/explore", providers);
		Response response = client.accept("application/json").type("application/json").post(exploration);

		Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

		MappingJsonFactory factory = new MappingJsonFactory();
		JsonParser parser = factory.createJsonParser((InputStream) response.getEntity());
		List<ExplorationResult> output = parser.readValueAs(new TypeReference<List<ExplorationResult>>() {
		});

		Assert.assertNotNull(output);
		Assert.assertTrue(output.size() == 0);
	}
}
