package controller.actions;

import controller.Controller;
import controller.InputHandler;
import controller.InputValidator;
import model.db.Database;
import model.street.Apartment;
import model.street.Building;
import model.street.Penthouse;
import view.TextualApartmentsQuery;
import view.menu.Action;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents an action for creating a Square
 */

public class QueryPenthouseAptAction implements Action {//Option2Query6

	/**
	 * Default constructor
	 */
	public QueryPenthouseAptAction() {

	}

	/**
	 * This action queries about all penthouse apartments
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
				if (apartment instanceof Penthouse) {
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
