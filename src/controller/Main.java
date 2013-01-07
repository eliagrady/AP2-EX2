/**
 * Ex 3
 * Author: Elia Grady
 * ID: 300907060
 * Course: Advanced Programming 1
 * T.A.: Igor Rochlin
 * Represents the Main function.
 * Creates a controller and run it's predefined sequence
 */

package controller;

import controller.errors.InvalidInputException;

public class Main {
	public static void main(String[] args) throws InvalidInputException {
		// Open program - load configuration to DB
		Controller controller = Controller.getInstance();
		controller.getDatabaseInstance().restoreState();
		try {
			controller.mainSequence();
		} catch (Throwable e) {
			controller.getDatabaseInstance().saveState();
			System.out.println("Oops, program crashed. Don't worry, saving changes");
		}
		// Close program - configuration saved to DB by the controller
		//saving done by exiting correctly
	}
}
