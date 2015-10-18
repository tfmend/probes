package com.thiagofurtado;

/**
 * @author thiago
 */
public class Probe {

	private final Plateau plateau;

	private final Position position;

	private Direction direction;

	/**
	 * @param plateau
	 * @param path
	 * @param direction
	 */
	public Probe(Plateau plateau, Position path, Direction direction) {
		super();
		this.plateau = plateau;
		this.position = path;
		this.direction = direction;
	}

	/**
	 * @return position
	 */
	public Position getPosition() {
		return this.position;
	}

	/**
	 * @return direction
	 */
	public Direction getDirection() {
		return this.direction;
	}

	/**
	 * @param moveTo
	 */
	public void move(Direction moveTo) {

		switch (moveTo) {
		case NORTH:
			if (this.position.getY() == (this.plateau.getyAxisLength() - 1)) {
				throw new IllegalArgumentException("Not possible to move towards north");
			}
			this.position.setY(this.position.getY() + 1);
			break;
		case SOUTH:
			if (this.position.getY() == 0) {
				throw new IllegalArgumentException("Not possible to move towards south");
			}
			this.position.setY(this.position.getY() - 1);
			break;
		case EAST:
			if (this.position.getX() == (this.plateau.getxAxisLength())) {
				throw new IllegalArgumentException("Not possible to move towards east");
			}
			this.position.setX(this.position.getX() + 1);
			break;
		case WEST:
			if (this.position.getX() == 0) {
				throw new IllegalArgumentException("Not possible to move towards west");
			}
			this.position.setX(this.position.getX() - 1);
			break;
		default:
			throw new IllegalArgumentException("Direction " + moveTo + " does not exist. Available ones are ");
		}

		/* updates direction */
		this.direction = moveTo;
	}
}
