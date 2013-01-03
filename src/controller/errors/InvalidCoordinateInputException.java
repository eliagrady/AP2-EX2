/**
 * Represents an Exception that occurs when getting invalid input as coordinate (out of range)
 */
package controller.errors;

@SuppressWarnings("serial")
public class InvalidCoordinateInputException extends InvalidInputException {
	public InvalidCoordinateInputException() {
	}

	@Override
	public String getMessage() {
		return "This coordinate is illegal.";
	}
}
