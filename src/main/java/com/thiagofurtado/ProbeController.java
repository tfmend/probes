package com.thiagofurtado;

/**
 * @author thiago
 */
public class ProbeController {

	private final Probe probe;
	private final String commands;
	private final Plateau plateau;

	/**
	 * @param probe
	 * @param commands
	 * @param plateau
	 */
	public ProbeController(Probe probe, String commands, Plateau plateau) {
		this.probe = probe;
		this.commands = commands;
		this.plateau = plateau;
	}

	/**
	 * Takes a sequence of commands and execute them to move probe over the
	 * plateau
	 *
	 * Allowed commands are L, R and M.
	 *
	 * @param commands
	 */
	public void execute(String commands) {
		for (char command : commands.toCharArray()) {
			switch (command) {
			case 'L':
				this.probe.turnLeft();
				break;
			case 'R':
				this.probe.turnRight();
				break;
			case 'M':
				this.probe.move();
				break;
			default:
				throw new IllegalArgumentException("Unknow command " + command);
			}
		}
	}

}
