package com.thiagofurtado;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author thiago
 */
public class ProbeControllerTest {

	private ProbeController controller;
	private Probe probe;
	private Plateau plateau;

	@Test
	public void testControllerInput1() {
		String commands = "LMLMLMLMM";
		Position initialPosition = new Position(1, 2);
		Direction initialDirection = Direction.NORTH;

		this.plateau = new Plateau(5, 5);
		this.probe = new Probe(initialPosition, initialDirection);
		this.controller = new ProbeController(this.probe, commands, this.plateau);

		this.controller.execute(commands);

		Assert.assertEquals(new Position(1, 3), this.probe.getPosition());
		Assert.assertEquals(Direction.NORTH, this.probe.getDirection());
	}

	@Test
	public void testControllerInput2() {
		String commands = "MMRMMRMRRM";
		Position initialPosition = new Position(3, 3);
		Direction initialDirection = Direction.EAST;

		this.plateau = new Plateau(5, 5);
		this.probe = new Probe(initialPosition, initialDirection);
		this.controller = new ProbeController(this.probe, commands, this.plateau);

		this.controller.execute(commands);

		Assert.assertEquals(new Position(5, 1), this.probe.getPosition());
		Assert.assertEquals(Direction.EAST, this.probe.getDirection());
	}

	@Test
	public void testControllerOutOfPlateauBounds() {
		String commands = "MMMMMM";
		Position initialPosition = new Position(3, 3);
		Direction initialDirection = Direction.EAST;

		this.plateau = new Plateau(5, 5);
		this.probe = new Probe(initialPosition, initialDirection);
		this.controller = new ProbeController(this.probe, commands, this.plateau);

		try {
			this.controller.execute(commands);
			Assert.fail("It should not pass");
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		Assert.assertEquals(new Position(5, 3), this.probe.getPosition());
		Assert.assertEquals(Direction.EAST, this.probe.getDirection());
	}
}
