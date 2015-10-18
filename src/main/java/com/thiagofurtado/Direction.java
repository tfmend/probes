package com.thiagofurtado;

import java.io.ObjectStreamException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author thiago
 */
public enum Direction {
	NORTH, EAST, SOUTH, WEST;

	/**
	 * Unmodifiable list of the enum values.
	 */
	private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(Direction.values()));

	/**
	 * Unmodifiable list of the enum values.
	 * <p>
	 * Use this list to avoid creating a new array instance every time the
	 * method {@link #values()} is invoked.
	 * </p>
	 *
	 * @return unmodifiable list in declaration order
	 */
	public static List<Direction> getValues() {
		return VALUES;
	}

	/**
	 * Returns the enum constant with the given unique identification. The
	 * unique identification used is the one returned by the method
	 * {@link #getId()}.
	 *
	 * @param id
	 *            enum identification (order)
	 * @return enum constant for the given id
	 * @throws IndexOutOfBoundsException
	 *             if no enum has the given ID
	 */
	public static Direction valueOf(int id) {
		return VALUES.get(id);
	}

	/**
	 * Returns the enum declaration order as unique identification.
	 *
	 * @return declaration order
	 */
	public int getId() {
		return this.ordinal();
	}

	/**
	 * WORKAROUND Enum deserialize bug after IIOP transmission.
	 *
	 * @return VM singleton enum
	 * @throws ObjectStreamException
	 */
	private Object readResolve() throws ObjectStreamException {
		return Direction.valueOf(this.name());
	}
}
