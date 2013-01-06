/**
 * Represents an Input Validator according to this program's logic.
 */
package controller;

import controller.errors.InvalidApartmentAreaInputException;
import controller.errors.InvalidApartmentAuxAreaInputException;
import controller.errors.InvalidInputException;

public class InputValidator {
	private static final InputValidator instance = new InputValidator();
	private static String currentBuildingAddress;

	/**
	 * Get the instance of this Input validator
	 *
	 * @return the instance of this Input validator
	 */
	public static InputValidator getInstance() {
		return instance;
	}

	/**
	 * Validates the street name for the building
	 *
	 * @param streetName the street name to validate
	 * @return the validated street name
	 */
	public String validateStreetName(String streetName) {
		boolean validInput = false;
		do {
			try {
				if (streetName != null && streetName.length() != 0) {
					validInput = true;
				} else throw new InvalidInputException();
			} catch (InvalidInputException e) {
				InputHandler inputHandler = InputHandler.getInstance();
				System.out.println("Invalid input. A Street name must be a string of length > 0.");
				System.out.println("Please enter a valid street name:");
				inputHandler.captureString();
				streetName = inputHandler.getCapturedString();
			}
		} while (!validInput);
		return streetName;
	}

	/**
	 * Validates the street number for the building
	 *
	 * @param streetNumber the street number to validate
	 * @return the validated y coordinate
	 */
	public int validateStreetNumber(int streetNumber) {
		boolean validInput = false;
		do {
			try {
				if (streetNumber > 0) {
					validInput = true;
				} else throw new InvalidInputException();
			} catch (InvalidInputException e) {
				InputHandler inputHandler = InputHandler.getInstance();
				System.out.println("Invalid input. A street number must be positive.");
				System.out.println("Please enter a valid street number.");
				inputHandler.captureInt();
				streetNumber = inputHandler.getCapturedInt();
			}
		} while (!validInput);
		return streetNumber;
	}

	/**
	 * Validates that a given input is between a given integer range
	 *
	 * @param upperBound the upper bound (MAX VALUE)
	 * @param lowerBound the lower bound (MIN VALUE)
	 * @param input      the input to validate
	 * @return true iff the input is between these bounds
	 */
	/*//TODO remove unused
	private boolean isIntInValidRange(int upperBound, int lowerBound, int input) {
		return lowerBound <= input && input <= upperBound;
	}
	*/

	/**
	 * Validates a given capacity input for a robot
	 *
	 * @param capacity capacity to validate
	 * @return a valid capacity
	 */

	public String getCurrentBuildingAddress() {
		return currentBuildingAddress;
	}

	public void setCurrentBuildingAddress(String currentBuildingAddress) {
		InputValidator.currentBuildingAddress = currentBuildingAddress;
	}

	/**
	 * Validates the floor number for the apartment
	 *
	 * @param floor the floor number to validate
	 * @return the validated floor number
	 */
	public int validateFloor(int floor) {
		boolean validInput = false;
		do {
			try {
				if (floor >= 0) {
					validInput = true;
				} else throw new InvalidInputException();
			} catch (InvalidInputException e) {
				InputHandler inputHandler = InputHandler.getInstance();
				System.out.println("Invalid input. A floor number must be non-negative.");
				System.out.println("Please enter a valid floor number.");
				inputHandler.captureInt();
				floor = inputHandler.getCapturedInt();
			}
		} while (!validInput);
		return floor;
	}

	/**
	 * Validates the number of rooms for the apartment
	 *
	 * @param numOfRooms the apartment's number of rooms to validate
	 * @return the validated number of rooms for an apartment
	 */
	public int validateNumOfRooms(int numOfRooms) {
		boolean validInput = false;
		do {
			try {
				if (numOfRooms > 0) {
					validInput = true;
				} else throw new InvalidInputException();
			} catch (InvalidInputException e) {
				InputHandler inputHandler = InputHandler.getInstance();
				System.out.println("Invalid input. An apartment number of rooms must be positive.");
				System.out.println("Please enter a valid number of rooms.");
				inputHandler.captureInt();
				numOfRooms = inputHandler.getCapturedInt();
			}
		} while (!validInput);
		return numOfRooms;
	}

	/**
	 * Validates the input for a area of an apartment
	 *
	 * @param area the input for a area of an apartment to validate
	 * @return a valid apartment's area input
	 */
	public float validateArea(float area) {
		boolean validInput = false;
		do {
			try {
				if (area > 0) {
					validInput = true;
				} else throw new InvalidApartmentAreaInputException();
			} catch (InvalidApartmentAreaInputException e) {
				InputHandler inputHandler = InputHandler.getInstance();
				System.out.println(e.getMessage());
				System.out.println("Please enter a valid positive Float");
				inputHandler.captureFloat();
				area = inputHandler.getCapturedFloat();
			}
		} while (!validInput);
		return area;
	}

	/**
	 * Validates the input for a area of an apartment's auxiliary area
	 * Can be a Warehouse area, a balcony area or a garden's area.
	 *
	 * @param area the input for a area of an apartment's auxiliary area to validate
	 * @return a valid apartment's auxiliary area input
	 */
	public float validateAuxArea(float area) {
		boolean validInput = false;
		do {
			try {
				if (area >= 0) {
					validInput = true;
				} else throw new InvalidApartmentAuxAreaInputException();
			} catch (InvalidApartmentAreaInputException e) {
				InputHandler inputHandler = InputHandler.getInstance();
				System.out.println(e.getMessage());
				System.out.println("Please enter a valid non-negative Float");
				inputHandler.captureFloat();
				area = inputHandler.getCapturedFloat();
			}
		} while (!validInput);
		return area;
	}

	/**
	 * Validates the street name for the building
	 *
	 * @param residentName the resident name to validate
	 * @return the validated resident name
	 */
	public String validateResidentName(String residentName) {
		boolean validInput = false;
		do {
			try {
				if (residentName != null && residentName.length() != 0) {
					validInput = true;
				} else throw new InvalidInputException();
			} catch (InvalidInputException e) {
				InputHandler inputHandler = InputHandler.getInstance();
				System.out.println("Invalid input. A resident's name must be a string of length > 0.");
				System.out.println("Please enter a valid resident name:");
				inputHandler.captureString();
				residentName = inputHandler.getCapturedString();
			}
		} while (!validInput);
		return residentName;
	}

}
