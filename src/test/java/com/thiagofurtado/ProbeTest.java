package com.thiagofurtado;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author thiago
 */
public class ProbeTest {

	Probe probe;

	@Before
	public void setUp() {
		Plateau plateau = new Plateau(5, 5);
		this.probe = new Probe(plateau, new Position(0, 0), Direction.EAST);
	}

	@Test
	public void testMoveToValidPosition() {
		this.probe.move(Direction.NORTH);
		Assert.assertEquals(1, this.probe.getPosition().getY());
		this.probe.move(Direction.EAST);
		Assert.assertEquals(1, this.probe.getPosition().getX());
		this.probe.move(Direction.SOUTH);
		Assert.assertEquals(0, this.probe.getPosition().getY());
		this.probe.move(Direction.WEST);
		Assert.assertEquals(0, this.probe.getPosition().getX());
	}

	@Test
	public void testMoveToInvalidPosition() {
		try {
			this.probe.move(Direction.SOUTH);
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}
}
