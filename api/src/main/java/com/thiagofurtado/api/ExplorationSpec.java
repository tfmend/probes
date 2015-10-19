package com.thiagofurtado.api;

import java.util.List;

public class ExplorationSpec {

	/*
	 * Input first line
	 */
	private int plateauXBound;
	private int plateauYBound;

	private List<ProbeExplorationSpec> probePaths;

	/**
	 * Empty constructor required by Jackon JSON Binding
	 */
	public ExplorationSpec() {
	}

	/**
	 * @return plateauXBound
	 */
	public int getPlateauXBound() {
		return this.plateauXBound;
	}

	/**
	 * @param plateauXBound
	 */
	public void setPlateauXBound(int plateauXBound) {
		this.plateauXBound = plateauXBound;
	}

	/**
	 * @return plateauYBound
	 */
	public int getPlateauYBound() {
		return this.plateauYBound;
	}

	/**
	 * @param plateauYBound
	 */
	public void setPlateauYBound(int plateauYBound) {
		this.plateauYBound = plateauYBound;
	}

	/**
	 * @return probePaths
	 */
	public List<ProbeExplorationSpec> getProbePaths() {
		return this.probePaths;
	}

	/**
	 * @param probePaths
	 */
	public void setProbePaths(List<ProbeExplorationSpec> probePaths) {
		this.probePaths = probePaths;
	}

	@Override
	public String toString() {
		return "ExplorationSpec [plateauXBound=" + this.plateauXBound + ", plateauYBound=" + this.plateauYBound
				+ ", probePaths=" + this.probePaths + "]";
	}
}
