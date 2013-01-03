/**
 * Represents a perfect triangle creation action.
 */
package controller.actions;

import controller.Controller;
import controller.InputHandler;
import controller.InputValidator;
import model.db.Database;
import model.street.Apartment;
import model.street.Building;
import model.street.RegularApartment;
import view.TextualApartmentsQuery;
import view.menu.Action;
import view.menu.TextualOption;

import java.util.ArrayList;
import java.util.Collections;

public class QueryFreeRegAptRoomsAddressAction implements Action {//Option2Query3

	/**
	 * Default constructor
	 */
	public QueryFreeRegAptRoomsAddressAction() {
	}


	/**
	 * This action queries about all free regular apartments at a given address with a given number of rooms
	 */
	@Override
	public void doAction() {
		InputHandler inputHandler = InputHandler.getInstance();
		InputValidator inputValidator = InputValidator.getInstance();

		TextualOption oStreetName = new TextualOption("What street address would you like to query?", new GetStringInputAction());
		oStreetName.displayOption();
		oStreetName.getAction().doAction();
		String streetName = inputHandler.getCapturedString();
		streetName = inputValidator.validateStreetName(streetName);

		TextualOption oApartmentNumberOfRooms = new TextualOption("What number of rooms would you like to query?", new GetIntegerInputAction());
		oApartmentNumberOfRooms.displayOption();
		oApartmentNumberOfRooms.getAction().doAction();
		int numOfRooms = inputHandler.getCapturedInt();
		numOfRooms = inputValidator.validateNumOfRooms(numOfRooms);

		ArrayList<Building> buildingsAtAddress = new ArrayList<Building>();
		ArrayList<Apartment> freeRegularApartmentsAddressRooms = new ArrayList<Apartment>();
		Database database = Controller.getInstance().getDatabaseInstance();
		for (Building building : database.getBuildings().values()) {
			if (building.getAddress().contentEquals(streetName)) {
				buildingsAtAddress.add(building);
			}
		}
		for (Building building : buildingsAtAddress) {
			for (Apartment apartment : building.getApartments()) {
				if (apartment instanceof RegularApartment && apartment.isFree() && apartment.getNumOfRooms() == numOfRooms) {
					freeRegularApartmentsAddressRooms.add(apartment);
				}
			}
		}

		Collections.sort(freeRegularApartmentsAddressRooms);
		TextualApartmentsQuery.getInstance().showQuery(freeRegularApartmentsAddressRooms);
		//No options to choose from: go back to the Main Menu
		Action returnToMain = new ReturnToMainMenuAction();
		returnToMain.doAction();
	}
}
