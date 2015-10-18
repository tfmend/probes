package com.thiagofurtado;

import java.util.List;

public class ExplorationSpec {
	private int[] coords;
	private List<Position> probePaths;

	/**
	 * @return coords
	 */
	public int[] getCoords() {
		return this.coords;
	}

	/**
	 * @param coords
	 */
	public void setCoords(int[] coords) {
		this.coords = coords;
	}

	/**
	 * @return probePaths
	 */
	public List<Position> getProbePaths() {
		return this.probePaths;
	}

	/**
	 * @param probePaths
	 */
	public void setProbePaths(List<Position> probePaths) {
		this.probePaths = probePaths;
	}
}
