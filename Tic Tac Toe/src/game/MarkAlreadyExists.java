package game;

/**
 * Description: A custom exception created to handle case where user
 * tries to place a mark where one already exists on the game board.
 *
 * @author Sam Hilliard
 * @version 1.0
 */

public class MarkAlreadyExists extends Exception {
	private static final long serialVersionUID = 1L;

	public MarkAlreadyExists(String message) {
		super(message);
	}
}
