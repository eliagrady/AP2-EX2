package controller.actions;

import controller.Controller;
import controller.InputHandler;
import controller.InputValidator;
import model.db.Database;
import model.street.Apartment;
import model.street.Building;
import model.street.GardenApartment;
import view.TextualApartmentsQuery;
import view.menu.Action;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents an action for creating a Square
 */

public class QueryGardenAptAction implements Action {//Option2Query7

	/**
	 * Default constructor
	 */
	public QueryGardenAptAction() {

	}

	/**
	 * This action queries about all garden apartments
	 */
	@Override
	public void doAction() {
		InputHandler inputHandler = InputHandler.getInstance();
		InputValidator inputValidator = InputValidator.getInstance();

		ArrayList<Building> buildingsAtAddress = new ArrayList<Building>();
		ArrayList<Apartment> penthouses = new ArrayList<Apartment>();
		Database database = Controller.getInstance().getDatabaseInstance();
		for (Building building : database.getBuildings().values()) {
			for (Apartment apartment : building.getApartments()) {
				if (apartment instanceof GardenApartment) {
					penthouses.add(apartment);
				}
			}
		}

		Collections.sort(penthouses);
		TextualApartmentsQuery.getInstance().showQuery(penthouses);
		//No options to choose from: go back to the Main Menu
		Action returnToMain = new ReturnToMainMenuAction();
		returnToMain.doAction();
	}
}
