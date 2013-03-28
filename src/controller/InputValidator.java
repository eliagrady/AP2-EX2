/**
 * Represents an Input Validator according to this program's logic.
 */
package controller;

import controller.errors.InvalidApartmentAreaInputException;
import controller.errors.InvalidApartmentAuxAreaInputException;
import controller.errors.InvalidFilenameException;
import controller.errors.InvalidInputException;

import java.io.File;

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
     * Return the current building address being created
     *
     * @return the address of the current building
     */
    public String getCurrentBuildingAddress() {
        return currentBuildingAddress;
    }

    /**
     * Sets the current building's address that is being created
     *
     * @param currentBuildingAddress the address of the current building being created
     */
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
            } catch (InvalidApartmentAuxAreaInputException e) {
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

    public String validatePrivateEntrance(String hasPrivateEntrance) {
        boolean validInput = false;
        do {
            try {
                if (hasPrivateEntrance != null) {
                    if (hasPrivateEntrance.equalsIgnoreCase("y") || hasPrivateEntrance.equalsIgnoreCase("n")) {
                        validInput = true;
                    } else throw new InvalidInputException();
                } else throw new InvalidInputException();
            } catch (InvalidInputException e) {
                InputHandler inputHandler = InputHandler.getInstance();
                System.out.println("Invalid input. Please enter Y or N .");
                inputHandler.captureString();
                hasPrivateEntrance = inputHandler.getCapturedString();
            }
        } while (!validInput);
        return hasPrivateEntrance;//Can return bool, but it's not a part of the design
    }

    /**
     * Sanity check
     * Validate the input file for existence and ability to read from (not integrity)
     *
     * @param filename the file name to check
     * @return the string of the filename or null if passed the number of attempts
     */
    public String validateFileName(String filename) {

        // 3 mistypes of filename are allowed
        boolean validInput = false;
        File file;
        int retryCount = 1;
        do {
            file = new File(filename);
            try {
                if (filename != null && filename.length() != 0 && file.exists() && file.canWrite()) {
                    validInput = true;
                    return filename;
                } else throw new InvalidFilenameException();
            } catch (InvalidFilenameException e) {
                InputHandler inputHandler = InputHandler.getInstance();
                System.out.println("Invalid file name input (#" + retryCount + " attempt).");
                System.out.println("Please enter a valid file name:");
                inputHandler.captureString();
                filename = inputHandler.getCapturedString();
                file = new File(filename);
                retryCount++;
            }
        } while (!validInput && retryCount < 3);
        return null;
    }

    /**
     * Validates the apartment's number ('dira')
     *
     * @param apartmentNumber the apartment's number  to validate
     * @return the validated number for an apartment
     */
    public int validateApartmentNum(int apartmentNumber) {
        boolean validInput = false;
        do {
            try {
                if (apartmentNumber >= 0) {
                    validInput = true;
                } else throw new InvalidInputException();
            } catch (InvalidInputException e) {
                InputHandler inputHandler = InputHandler.getInstance();
                System.out.println("Invalid input. An apartment number of rooms must be non-negative.");
                System.out.println("Please enter a valid number.");
                inputHandler.captureInt();
                apartmentNumber = inputHandler.getCapturedInt();
            }
        } while (!validInput);
        return apartmentNumber;
    }
}
