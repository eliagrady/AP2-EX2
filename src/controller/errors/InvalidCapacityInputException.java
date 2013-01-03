/**
 * Represents an Exception that occurs when getting an invalid input for robot capacity (negative).
 */
package controller.errors;

@SuppressWarnings("serial")
public class InvalidCapacityInputException extends InvalidInputException {
	public InvalidCapacityInputException() {

	}

	@Override
	public String getMessage() {
		return "This coordinate is illegal.";
	}
}
