/**
 * Represents an Exception regarding assigning an object to a non-free location.
 */
package model.errors;


@SuppressWarnings("serial")
public class LocationIsNotFreeException extends Exception {
	/**
	 * Default constructor
	 */
	public LocationIsNotFreeException() {

	}

	/**
	 * A string representation of this exception
	 *
	 * @return the string representation of this exception
	 */
	@Override
	public String getMessage() {
		return "Specified location is already taken, and is not available.";
	}
}
