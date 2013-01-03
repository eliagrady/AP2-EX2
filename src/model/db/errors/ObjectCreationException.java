/**
 * Represents an Exception that occurs when the creation of an object has failed
 */
package model.db.errors;

@SuppressWarnings("serial")
public class ObjectCreationException extends Exception {
	/**
	 * Default constructor
	 */
	public ObjectCreationException() {

	}

	/**
	 * A string representation of this exception
	 *
	 * @return the string representation of this exception
	 */
	@Override
	public String getMessage() {
		return "Cannot create this object";
	}
}
