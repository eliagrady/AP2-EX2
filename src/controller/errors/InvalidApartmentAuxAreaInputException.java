/**
 * Represents an Exception that occurs when getting an invalid input for an apartment's auxiliary area.
 */
package controller.errors;

public class InvalidApartmentAuxAreaInputException extends InvalidApartmentAreaInputException {
	public InvalidApartmentAuxAreaInputException() {

	}

	@Override
	public String getMessage() {
		return "An apartment's auxiliary area must be a non negative number.";
	}
}
