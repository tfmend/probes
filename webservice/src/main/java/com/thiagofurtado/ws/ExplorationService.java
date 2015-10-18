package com.thiagofurtado.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thiagofurtado.api.ExplorationResult;
import com.thiagofurtado.api.ExplorationSpec;
import com.thiagofurtado.api.ProbeExplorationSpec;

@Path("/")
public class ExplorationService {

	@POST
	@Path("/explore")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response explore(ExplorationSpec explorationSpec) {

		if (explorationSpec == null) {
			return Response.status(400).build();
		} else if (explorationSpec.getPlateauXBound() < 1 || explorationSpec.getPlateauYBound() < 1) {
			return Response.status(400).build();
		} else if (explorationSpec.getProbePaths() == null) {
			return Response.status(400).build();
		}

		Plateau plateau = new Plateau(explorationSpec.getPlateauXBound(), explorationSpec.getPlateauYBound());

		List<ExplorationResult> explorationResult = new ArrayList<>();
		for (ProbeExplorationSpec probeSpec : explorationSpec.getProbePaths()) {
			Probe probe = new Probe(probeSpec.getInitialPosition(), probeSpec.getInitialDirection());
			String commands = probeSpec.getPath();
			try {
				ProbeController controller = new ProbeController(probe, commands, plateau);

				controller.execute(commands);

				explorationResult.add(new ExplorationResult(probe.getPosition(), probe.getDirection()));
			} catch (Exception e) {
				return Response.serverError().entity(e.getMessage()).build();
			}
		}

		return Response.ok().entity(explorationResult).build();

	}
}
