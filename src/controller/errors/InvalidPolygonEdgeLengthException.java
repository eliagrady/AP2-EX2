/**
 * Represents an Exception that occurs when getting an invalid input for polygon edge (non-positive).
 */
package controller.errors;

@SuppressWarnings("serial")
public class InvalidPolygonEdgeLengthException extends InvalidInputException {
	public InvalidPolygonEdgeLengthException() {
	}

	@Override
	public String getMessage() {
		return "A polygon must have a positive edge length.";
	}
}
