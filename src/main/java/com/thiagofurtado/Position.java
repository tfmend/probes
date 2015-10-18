package com.thiagofurtado;

/**
 * @author thiago
 */
public class Position {

	private int x;
	private int y;

	/**
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return x
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return y
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

}
