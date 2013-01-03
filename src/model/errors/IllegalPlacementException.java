/**
 * Represents an Exception that occur when trying to add a robot to an already taken spot.
 */
package model.errors;


@SuppressWarnings("serial")
public class IllegalPlacementException extends IllegalMoveException {
	/**
	 * Default constructor
	 */
	public IllegalPlacementException() {

	}

	/**
	 * A string representation of this exception
	 *
	 * @return the string representation of this exception
	 */
	@Override
	public String getMessage() {
		return "Specified location to add robot is already taken.";
	}
}
