package com.thiagofurtado;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

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

		Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
}
