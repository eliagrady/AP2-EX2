/**
 * Represents a query action for regular apartments by address.
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

public class QueryRegAptAddressAction implements Action {//Option2Query4

	/**
	 * Default constructor
	 */
	public QueryRegAptAddressAction() {
	}

	/**
	 * This action queries about all regular apartments at a given address
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

		ArrayList<Building> buildingsAtAddress = new ArrayList<Building>();
		ArrayList<Apartment> freeRegularApartmentsAddress = new ArrayList<Apartment>();
		Database database = Controller.getInstance().getDatabaseInstance();
		for (Building building : database.getBuildings().values()) {
			if (building.getAddress().contentEquals(streetName)) {
				buildingsAtAddress.add(building);
			}
		}
		for (Building building : buildingsAtAddress) {
			for (Apartment apartment : building.getApartments()) {
				if (apartment instanceof RegularApartment) {
					freeRegularApartmentsAddress.add(apartment);
				}
			}
		}

		Collections.sort(freeRegularApartmentsAddress);
		TextualApartmentsQuery.getInstance().showQuery(freeRegularApartmentsAddress);
		//No options to choose from: go back to the Main Menu
		Action returnToMain = new ReturnToMainMenuAction();
		returnToMain.doAction();
	}
}
