/**
 * Represents an Exception that occurs when getting an invalid input for an apartment's area.
 */
package controller.errors;

@SuppressWarnings("serial")
public class InvalidApartmentAreaInputException extends InvalidInputException {
	public InvalidApartmentAreaInputException() {

	}

	@Override
	public String getMessage() {
		return "An apartment's area must be a positive number.";
	}
}
