/**
 * Represents a query action about all regular apartments
 */

package controller.actions;

import controller.Controller;
import model.db.Database;
import model.street.Apartment;
import model.street.Building;
import model.street.RegularApartment;
import view.TextualApartmentsQuery;
import view.menu.Action;

import java.util.ArrayList;
import java.util.Collections;

public class QueryRegAptAction implements Action {//Option2Query5

	/**
	 * Default contractor
	 */
	public QueryRegAptAction() {
	}

	/**
	 * This action queries about all regular apartments
	 */
	@Override
	public void doAction() {
		ArrayList<Apartment> regularApartments = new ArrayList<Apartment>();
		Database database = Controller.getInstance().getDatabaseInstance();
		for (Building building : database.getBuildings().values()) {
			for (Apartment apartment : building.getApartments()) {
				if (apartment instanceof RegularApartment) {
					regularApartments.add(apartment);
				}
			}
		}

		Collections.sort(regularApartments);
		TextualApartmentsQuery.getInstance().showQuery(regularApartments);
		//No options to choose from: go back to the Main Menu
		Action returnToMain = new ReturnToMainMenuAction();
		returnToMain.doAction();
	}
}
