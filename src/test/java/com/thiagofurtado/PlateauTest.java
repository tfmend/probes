package com.thiagofurtado;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author thiago
 */
public class PlateauTest {

	@Test
	public void assertionTest() {
		try {
			Plateau invalidArgs = new Plateau(-1, 10);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}

}
