package com.thiagofurtado.cli;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.thiagofurtado.api.ExplorationSpec;

/**
 * @author thiago
 */
public class ExplorationSpecBuilderTest {

	private final ExplorationSpecBuilder builder = new ExplorationSpecBuilder();

	@Test
	public void testValidInput() {
		File input = new File(ExplorationSpecBuilderTest.class.getClassLoader().getResource("input.txt").getFile());
		ExplorationSpec specInput = this.builder.buildExplorationSpecFromFile(input);

		Assert.assertNotNull(specInput);
		Assert.assertEquals(6, specInput.getPlateauYBound());
		Assert.assertEquals(5, specInput.getPlateauXBound());
		Assert.assertEquals(2, specInput.getProbePaths().size());
	}

	@Test
	public void testInputWithoutProbes() {
		File inputNoProbes = new File(ExplorationSpecBuilderTest.class.getClassLoader().getResource("input_no_probes.txt").getFile());
		ExplorationSpec specInputNoProbes = this.builder.buildExplorationSpecFromFile(inputNoProbes);

		Assert.assertNotNull(specInputNoProbes);
		Assert.assertEquals(3, specInputNoProbes.getPlateauYBound());
		Assert.assertEquals(5, specInputNoProbes.getPlateauXBound());
		Assert.assertEquals(0, specInputNoProbes.getProbePaths().size());
	}

	@Test(expected = RuntimeException.class)
	public void testInvalidInput() {
		File inputInvalid = new File(ExplorationSpecBuilderTest.class.getClassLoader().getResource("input_invalid.txt").getFile());
		ExplorationSpec specInputInvalid = this.builder.buildExplorationSpecFromFile(inputInvalid);
	}

	@Test(expected = RuntimeException.class)
	public void testInvalidInput1() {
		File inputInvalid1 = new File(ExplorationSpecBuilderTest.class.getClassLoader().getResource("input_invalid1.txt").getFile());
		ExplorationSpec specInputInvalid1 = this.builder.buildExplorationSpecFromFile(inputInvalid1);
	}

	@Test(expected = RuntimeException.class)
	public void testInvalidInputEmpty() {
		File inputInvalidEmpty = new File(ExplorationSpecBuilderTest.class.getClassLoader().getResource("input_empty.txt").getFile());
		ExplorationSpec specInputInvalidEMpty = this.builder.buildExplorationSpecFromFile(inputInvalidEmpty);
	}
}
