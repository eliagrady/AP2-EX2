/**
 * Represents an Exception that occurs when selecting an invalid selection.
 */
package view.menu.errors;

@SuppressWarnings("serial")
public class InvalidSelectionException extends Exception {
	/**
	 * Default constructor
	 */
	public InvalidSelectionException() {

	}

	/**
	 * Retrieve the message for this exception
	 *
	 * @return a string representation of this exception's message.
	 */
	@Override
	public String getMessage() {
		return "Invalid selection";
	}
}
