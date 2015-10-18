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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.x;
		result = prime * result + this.y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Position other = (Position) obj;
		if (this.x != other.x) {
			return false;
		}
		if (this.y != other.y) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Position [x=" + this.x + ", y=" + this.y + "]";
	}

}
