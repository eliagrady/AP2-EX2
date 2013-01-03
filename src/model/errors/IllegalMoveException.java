/**
 * Represents an Exception that occur when trying to make an illegal move.
 */
package model.errors;


@SuppressWarnings("serial")
public class IllegalMoveException extends Exception {
	/**
	 * Default constructor
	 */
	public IllegalMoveException() {

	}

	/**
	 * A string representation of this exception
	 *
	 * @return the string representation of this exception
	 */
	@Override
	public String getMessage() {
		return "Specified location to move to is illegal.";
	}
}
