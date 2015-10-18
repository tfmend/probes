package com.thiagofurtado.ws;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thiagofurtado.api.Direction;
import com.thiagofurtado.api.Position;
import com.thiagofurtado.ws.Probe;

/**
 * @author thiago
 */
public class ProbeTest {

	Probe probe;

	@Before
	public void setUp() {
		this.probe = new Probe(new Position(0, 0), Direction.EAST);
	}

	@Test
	public void testMoveToValidPosition() {
		this.probe.turnLeft();
		Assert.assertEquals(Direction.NORTH, this.probe.getDirection());
		this.probe.turnLeft();
		Assert.assertEquals(Direction.WEST, this.probe.getDirection());
		this.probe.turnLeft();
		Assert.assertEquals(Direction.SOUTH, this.probe.getDirection());
		this.probe.turnLeft();
		Assert.assertEquals(Direction.EAST, this.probe.getDirection());
		this.probe.turnLeft();
		Assert.assertEquals(Direction.NORTH, this.probe.getDirection());
		this.probe.turnLeft();
		Assert.assertEquals(Direction.WEST, this.probe.getDirection());

		this.probe.turnRight();
		Assert.assertEquals(Direction.NORTH, this.probe.getDirection());
		this.probe.turnRight();
		Assert.assertEquals(Direction.EAST, this.probe.getDirection());
		this.probe.turnRight();
		Assert.assertEquals(Direction.SOUTH, this.probe.getDirection());
		this.probe.turnRight();
		Assert.assertEquals(Direction.WEST, this.probe.getDirection());
		this.probe.turnRight();
		Assert.assertEquals(Direction.NORTH, this.probe.getDirection());
		this.probe.turnRight();
		Assert.assertEquals(Direction.EAST, this.probe.getDirection());

		this.probe.move();
		Assert.assertEquals(1, this.probe.getPosition().getX());
		this.probe.turnLeft();
		this.probe.move();
		Assert.assertEquals(1, this.probe.getPosition().getX());
		Assert.assertEquals(1, this.probe.getPosition().getY());
		this.probe.turnRight();
		this.probe.move();
		Assert.assertEquals(2, this.probe.getPosition().getX());
		Assert.assertEquals(1, this.probe.getPosition().getY());
		this.probe.turnRight();
		this.probe.move();
		this.probe.move();
		/* Controllert must validate plateau bounds */
		Assert.assertEquals(-1, this.probe.getPosition().getY());
	}

}
