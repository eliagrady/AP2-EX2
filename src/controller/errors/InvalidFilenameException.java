/**
 * Represents an Exception that occurs when getting a general invalid input.
 */
package controller.errors;

@SuppressWarnings("serial")
public class InvalidFilenameException extends Exception {
    public InvalidFilenameException() {

    }

    @Override
    public String getMessage() {
        return "This input for the file name is illegal.";
    }
}
