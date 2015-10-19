package com.thiagofurtado.api;

/**
 * @author thiago
 */
public class ExplorationResult {

	private Position finalPosition;

	private Direction finalDirection;

	/**
	 * Empty constructor required by Jackson JSON Binding
	 */
	public ExplorationResult() {

	}

	/**
	 * @param finalPosition
	 * @param finalDirection
	 */
	public ExplorationResult(Position finalPosition, Direction finalDirection) {
		super();
		this.finalPosition = finalPosition;
		this.finalDirection = finalDirection;
	}

	/**
	 * @return finalPosition
	 */
	public Position getFinalPosition() {
		return this.finalPosition;
	}

	/**
	 * @param finalPosition
	 */
	public void setFinalPosition(Position finalPosition) {
		this.finalPosition = finalPosition;
	}

	/**
	 * @return finalDirection
	 */
	public Direction getFinalDirection() {
		return this.finalDirection;
	}

	/**
	 * @param finalDirection
	 */
	public void setFinalDirection(Direction finalDirection) {
		this.finalDirection = finalDirection;
	}

	@Override
	public String toString() {
		return this.finalPosition.getX() + " " + this.finalPosition.getY() + " "
				+ this.finalDirection.toString().charAt(0);
	}

}
