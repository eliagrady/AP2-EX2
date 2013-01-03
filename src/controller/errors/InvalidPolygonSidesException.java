/**
 * Represents an Exception that occurs when getting an invalid input for polygon sides (must be above 3).
 */
package controller.errors;

@SuppressWarnings("serial")
public class InvalidPolygonSidesException extends InvalidInputException {
	public InvalidPolygonSidesException() {
	}

	@Override
	public String getMessage() {
		return "A polygon must have at least 3 sides.";
	}
}
