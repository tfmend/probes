package com.thiagofurtado;

/**
 * @author thiago
 */
public class Plateau {

	private final int xAxisLength;
	private final int yAxisLength;

	/**
	 *
	 */
	public Plateau(int xAxisLength, int yAxisLength) {
		if (xAxisLength < 0 || yAxisLength < 0) {
			throw new IllegalArgumentException("A plateau has at least the point 0x0");
		}
		this.xAxisLength = xAxisLength;
		this.yAxisLength = yAxisLength;
	}

	/**
	 * @return xAxisLength
	 */
	public int getxAxisLength() {
		return this.xAxisLength;
	}

	/**
	 * @return yAxisLength
	 */
	public int getyAxisLength() {
		return this.yAxisLength;
	}

}
