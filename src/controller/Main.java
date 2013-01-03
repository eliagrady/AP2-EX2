/**
 * Ex 2
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
		//saving done by exiting correctly

		//controller.getDatabaseInstance().saveState();

		/// Mid program - run program display menu
		// Option 1: Add new buildings
		// Option 2: Display data about DB (queries)
		// Option 3: Change a building's data to 'Sell it'
		// Option 4: Save & Quit

		// Close program - save configuration to DB

	}
}
