/**
 * Represents a prefect polygon creation action.
 */
package controller.actions;

import controller.Controller;
import model.db.Database;
import model.street.Apartment;
import model.street.Building;
import view.TextualApartmentsQuery;
import view.menu.Action;

import java.util.ArrayList;
import java.util.Collections;

public class QueryFreeAptAction implements Action {//Option2Query7

    /**
     * Default constructor
     */
    public QueryFreeAptAction() {
    }

    /**
     * This action query about all free apartments and display this information
     */
    @Override
    public void doAction() {
        Database database = Controller.getInstance().getDatabaseInstance();
        ArrayList<Apartment> freeApartments = new ArrayList<Apartment>();
        for (Building building : database.getBuildings().values()) {
            for (Apartment apartment : building.getApartments()) {
                if (apartment.isFree()) {
                    freeApartments.add(apartment);
                }
            }
        }
        Collections.sort(freeApartments);
        TextualApartmentsQuery.getInstance().showQuery(freeApartments);
        //No options to choose from: go back to the Main Menu
        Action returnToMain = new ReturnToMainMenuAction();
        returnToMain.doAction();
    }
}
