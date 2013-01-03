/**
 * Represent an input grabber, implemented using the Scanner.
 */
package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
	private static final InputHandler instance = new InputHandler();
	private static Scanner input = new Scanner(System.in);
	private String capturedString;
	private int capturedInt;
	private float capturedFloat;

	/**
	 * Get the instance of this Input handler
	 *
	 * @return the instance of this Input handler
	 */
	public static InputHandler getInstance() {
		return instance;
	}

	/**
	 * Capture a string and updates the 'captured string' field
	 */
	public void captureString() {
		boolean validInput = false;
		do {
			try {
				this.capturedString = input.next();
				validInput = true;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Expected a String.");
				System.out.println("Please enter a valid String input.");
				input.nextLine();
			}
		} while (!validInput);
	}

	/**
	 * Capture an integer and updates the 'captured int' field
	 */
	public void captureInt() {
		boolean validInput = false;
		do {
			try {
				this.capturedInt = input.nextInt();
				validInput = true;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Expected an Integer.");
				System.out.println("Please enter a valid Integer input.");
				input.nextLine();
			}
		} while (!validInput);
	}

	/**
	 * Capture a float and updates the 'captured float' field
	 */
	public void captureFloat() {
		boolean validInput = false;
		do {
			try {
				this.capturedFloat = input.nextFloat();
				validInput = true;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Expected a Float.");
				System.out.println("Please enter a valid Float input.");
				input.nextLine();
			}
		} while (!validInput);
	}

	/**
	 * Get the captured string
	 *
	 * @return the captured string
	 */
	public String getCapturedString() {
		return capturedString;
	}

	/**
	 * Get the captured integer
	 *
	 * @return the captured integer
	 */
	public int getCapturedInt() {
		return capturedInt;
	}

	/**
	 * Get the captured float
	 *
	 * @return the captured float
	 */
	public float getCapturedFloat() {
		return capturedFloat;
	}
}
