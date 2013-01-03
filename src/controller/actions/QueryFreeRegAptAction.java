/**
 * Represents a prefect polygon creation action.
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

public class QueryFreeRegAptAction implements Action {//Option2Query1

	/**
	 * Default constructor
	 */
	public QueryFreeRegAptAction() {
	}

	/**
	 * This action queries about all free regular apartments
	 */
	@Override
	public void doAction() {
		Database database = Controller.getInstance().getDatabaseInstance();
		ArrayList<Apartment> freeRegularApartments = new ArrayList<Apartment>();
		for (Building building : database.getBuildings().values()) {
			for (Apartment apartment : building.getApartments()) {
				if (apartment instanceof RegularApartment && apartment.isFree()) {
					freeRegularApartments.add(apartment);
				}
			}
		}
		Collections.sort(freeRegularApartments);
		TextualApartmentsQuery.getInstance().showQuery(freeRegularApartments);
		//No options to choose from: go back to the Main Menu
		Action returnToMain = new ReturnToMainMenuAction();
		returnToMain.doAction();
	}
}
