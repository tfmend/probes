package com.thiagofurtado.api;

/**
 * @author thiago
 */
public class ProbeExplorationSpec {

	private String path;

	private Position initialPosition;

	private Direction initialDirection;

	/**
	 * Empty constructor required by Jackson JSON Binding
	 */
	public ProbeExplorationSpec() {
	}

	/**
	 * @param path
	 * @param initialPosition
	 * @param initialDirection
	 */
	public ProbeExplorationSpec(String path, Position initialPosition, Direction initialDirection) {
		super();
		this.path = path;
		this.initialPosition = initialPosition;
		this.initialDirection = initialDirection;
	}

	/**
	 * @return path
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return initialPosition
	 */
	public Position getInitialPosition() {
		return this.initialPosition;
	}

	/**
	 * @param initialPosition
	 */
	public void setInitialPosition(Position initialPosition) {
		this.initialPosition = initialPosition;
	}

	/**
	 * @return initialDirection
	 */
	public Direction getInitialDirection() {
		return this.initialDirection;
	}

	/**
	 * @param initialDirection
	 */
	public void setInitialDirection(Direction initialDirection) {
		this.initialDirection = initialDirection;
	}

}
