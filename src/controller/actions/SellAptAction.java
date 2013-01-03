/**
 * Represents a simulation and statistics on a current Ground configuration.
 */
package controller.actions;

import controller.Controller;
import controller.InputHandler;
import controller.InputValidator;
import controller.TextualMenuContent;
import model.db.Database;
import model.street.Apartment;
import model.street.Building;
import view.TextualConsole;
import view.menu.Action;
import view.menu.Option;
import view.menu.TextualMenu;
import view.menu.TextualOption;
import view.menu.errors.InvalidSelectionException;

import java.util.ArrayList;

public class SellAptAction implements Action {//Option3

	/**
	 * 'Sell' an apartment by changing the name of the resident
	 */
	@Override
	public void doAction() {
		Database database = Controller.getInstance().getDatabaseInstance();
		InputHandler inputHandler = InputHandler.getInstance();
		InputValidator inputValidator = InputValidator.getInstance();
		ArrayList<Option> oFreeApartments = new ArrayList<Option>();
		for (Building building : database.getBuildings().values()) {
			for (Apartment apartment : building.getApartments()) {
				if (apartment.isFree()) {
					oFreeApartments.add(new TextualSellOption(apartment, apartment.getDescription()));
				}
			}
		}
		TextualMenu mApartments = new TextualMenu(TextualMenuContent.CREATE_APARTMENTS_TITLE, oFreeApartments);
		TextualOption oConfirmation = new TextualOption(TextualMenuContent.SELL_CONFIRMATION, new GetStringInputAction());
		TextualOption oResident = new TextualOption("Please enter a new resident name for this apartment:", new GetStringInputAction());

		String message;
		boolean validInput;
		Boolean keepCreating = false;
		do {
			validInput = false;
			while (!validInput) {
				mApartments.showMenu();
				//Menu 1.1 options : What apartment to sell?
				InputHandler.getInstance().captureInt();
				int selection = inputHandler.getCapturedInt();
				try {
					TextualSellOption selected = (TextualSellOption) mApartments.getOption(selection);
					oResident.displayOption();
					oResident.getAction().doAction();
					String residentName = inputHandler.getCapturedString();
					residentName = inputValidator.validateStreetName(residentName);
					if (database.sellApartment(selected.getApartment(), residentName)) {
						TextualConsole.getInstance().println(TextualMenuContent.SELL_SUCCESS);
						oFreeApartments.remove(selected);
						mApartments.setOptions(oFreeApartments);
					} else {
						TextualConsole.getInstance().println(TextualMenuContent.SELL_FAILURE);
					}
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
