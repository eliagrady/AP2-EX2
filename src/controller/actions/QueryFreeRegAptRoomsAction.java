/**
 * Represents a query action about free regular apartments with a given number of rooms
 */
package controller.actions;

import controller.Controller;
import controller.InputHandler;
import controller.InputValidator;
import controller.TextualMenuContent;
import model.db.Database;
import model.street.Apartment;
import model.street.Building;
import model.street.RegularApartment;
import view.TextualApartmentsQuery;
import view.menu.Action;
import view.menu.TextualOption;

import java.util.ArrayList;
import java.util.Collections;

public class QueryFreeRegAptRoomsAction implements Action {//Option2Query2

	/**
	 * Default constructor
	 */
	public QueryFreeRegAptRoomsAction() {
	}

	/**
	 * This action queries about all free regular apartments with a given number of rooms
	 */
	@Override
	public void doAction() {
		InputHandler inputHandler = InputHandler.getInstance();
		InputValidator inputValidator = InputValidator.getInstance();
		TextualOption oApartmentNumberOfRooms = new TextualOption(TextualMenuContent.QUERY_NUMBER_OF_ROOMS, new GetIntegerInputAction());
		oApartmentNumberOfRooms.displayOption();
		oApartmentNumberOfRooms.getAction().doAction();
		int numOfRooms = inputHandler.getCapturedInt();
		numOfRooms = inputValidator.validateNumOfRooms(numOfRooms);
		Database database = Controller.getInstance().getDatabaseInstance();
		ArrayList<Apartment> freeRegularApartmentsRooms = new ArrayList<Apartment>();
		for (Building building : database.getBuildings().values()) {
			for (Apartment apartment : building.getApartments()) {
				if (apartment instanceof RegularApartment && apartment.isFree() && apartment.getNumOfRooms() == numOfRooms) {
					freeRegularApartmentsRooms.add(apartment);
				}
			}
		}
		Collections.sort(freeRegularApartmentsRooms);
		TextualApartmentsQuery.getInstance().showQuery(freeRegularApartmentsRooms);
		//No options to choose from: go back to the Main Menu
		Action returnToMain = new ReturnToMainMenuAction();
		returnToMain.doAction();
	}
}
