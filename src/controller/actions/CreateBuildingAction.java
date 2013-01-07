/**
 * Represents an action for creating a building.
 */
package controller.actions;

import controller.Controller;
import controller.InputHandler;
import controller.InputValidator;
import controller.TextualMenuContent;
import model.db.MyPrototypeFactory;
import model.db.errors.ObjectCreationException;
import model.street.Building;
import view.menu.*;
import view.menu.errors.InvalidSelectionException;

import java.util.ArrayList;

public class CreateBuildingAction implements Action {
	/**
	 * Default constructor
	 */
	public CreateBuildingAction() {

	}

	/**
	 * This action creates a new Building and updates the 'database' instance with the newly generated one
	 */
	@Override
	public void doAction() {
		Controller controller = Controller.getInstance();
		MyPrototypeFactory objectsFactory = Controller.getInstance().getFactory();
		InputHandler inputHandler = InputHandler.getInstance();
		InputValidator inputValidator = InputValidator.getInstance();
		Building building = null;
		TextualOption oStreetName = new TextualOption("Set street name for this building:", new GetStringInputAction());
		oStreetName.displayOption();
		oStreetName.getAction().doAction();
		String streetName = inputHandler.getCapturedString();
		streetName = inputValidator.validateStreetName(streetName);
		TextualOption oStreetNumber = new TextualOption("Set street number:", new GetIntegerInputAction());
		oStreetNumber.displayOption();
		oStreetNumber.getAction().doAction();
		int streetNumber = inputHandler.getCapturedInt();
		streetNumber = inputValidator.validateStreetNumber(streetNumber);
		try { //if address exist, add apartments
			building = (Building) objectsFactory.createObject("Building");
			building.setStreetName(streetName);
			building.setStreetNumber(streetNumber);
			if (!controller.getDatabaseInstance().isBuildingExist(building.getAddress())) {
				controller.getDatabaseInstance().addBuilding(building.getAddress(), building);
			}
		} catch (ObjectCreationException e) {
			System.out.println(e.getMessage());
		}
		//This is how newly created apartments know which building they are at.
		InputValidator.getInstance().setCurrentBuildingAddress(building.getAddress());
		ArrayList<Option> oApartments = new ArrayList<Option>(3);
		TextualOption oAptTypeRegular = new TextualOption(TextualMenuContent.CREATE_REGULAR_APT_OPTION, new CreateRegAptAction());
		TextualOption oAptTypePenthouse = new TextualOption(TextualMenuContent.CREATE_PENTHOUSE_APT_OPTION, new CreatePenthouseAptAction());
		TextualOption oAptTypeGarden = new TextualOption(TextualMenuContent.CREATE_GARDEN_APT_OPTION, new CreateGardenAptAction());
		oApartments.add(oAptTypeRegular);
		oApartments.add(oAptTypePenthouse);
		oApartments.add(oAptTypeGarden);
		Menu mApartments = new TextualMenu(TextualMenuContent.CREATE_APARTMENTS_TITLE, oApartments);
		TextualOption oConfirmation = new TextualOption("Build another apartment Y/N?", new GetStringInputAction());
		String message;
		boolean validInput;
		Boolean keepCreating = false;
		do {
			validInput = false;
			while (!validInput) {
				mApartments.showMenu();
				//Menu 1.1 options : What apartment to create?
				InputHandler.getInstance().captureInt();
				int selection = inputHandler.getCapturedInt();
				try {
					mApartments.getOption(selection).getAction().doAction();
					validInput = true;
				} catch (InvalidSelectionException e) {
					System.out.println(e.getMessage());
					validInput = false;
				}
			}
			//Menu 4 : Confirmation Menu
			validInput = false;
			while (!validInput) {
				oConfirmation.displayOption();
				oConfirmation.getAction().doAction();
				message = inputHandler.getCapturedString();
				if (message.equalsIgnoreCase("Y")) {
					validInput = true;
					keepCreating = true;
				} else if (message.equalsIgnoreCase("N")) {
					validInput = true;
					keepCreating = false;
				} else {
					System.out.println(TextualMenuContent.INVALID_INPUT_REQUEST);
				}
			}
		}
		while (keepCreating);
	}
}
