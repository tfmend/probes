package com.thiagofurtado.ws;

import com.thiagofurtado.api.Direction;
import com.thiagofurtado.api.Position;

/**
 * @author thiago
 */
public class Probe {

	private final Position position;

	private Direction direction;

	/**
	 * @param position
	 * @param direction
	 */
	public Probe(Position position, Direction direction) {
		super();
		this.position = position;
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
	 *
	 */
	public void move() {
		switch (this.direction) {
		case NORTH:
			this.position.setY(this.position.getY() + 1);
			break;
		case SOUTH:
			this.position.setY(this.position.getY() - 1);
			break;
		case EAST:
			this.position.setX(this.position.getX() + 1);
			break;
		case WEST:
			this.position.setX(this.position.getX() - 1);
			break;
		default:
			throw new IllegalStateException();
		}
	}

	/**
	 *
	 */
	public void turnLeft() {
		int ord = (this.direction.ordinal() - 1) % Direction.getValues().size();
		if (ord == -1) {
			ord = Direction.getValues().size() - 1;
		}
		this.direction = Direction.valueOf(ord);
	}

	/**
	 *
	 */
	public void turnRight() {
		this.direction = Direction.valueOf((this.direction.ordinal() + 1) % Direction.getValues().size());
	}
}
