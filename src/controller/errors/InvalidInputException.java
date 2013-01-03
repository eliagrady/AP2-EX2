/**
 * Represents an Exception that occurs when getting a general invalid input.
 */
package controller.errors;

@SuppressWarnings("serial")
public class InvalidInputException extends Exception {
	public InvalidInputException() {

	}

	@Override
	public String getMessage() {
		return "This input is illegal.";
	}
}
