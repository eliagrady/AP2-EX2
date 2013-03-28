/**
 * Represents a perfect triangle creation action.
 */
package controller.actions;

import controller.Controller;
import controller.InputHandler;
import controller.InputValidator;
import controller.TextualMenuContent;
import model.db.Database;
import model.street.Apartment;
import model.street.Building;
import view.TextualApartmentsQuery;
import view.menu.Action;
import view.menu.TextualOption;

import java.util.ArrayList;
import java.util.Collections;

public class QueryFreeAptAddressAction implements Action {//Option2Query8

    /**
     * Default constructor
     */
    public QueryFreeAptAddressAction() {
    }

    /**
     * This action query about all free apartments at given address and display this information
     */
    @Override
    public void doAction() {
        InputHandler inputHandler = InputHandler.getInstance();
        InputValidator inputValidator = InputValidator.getInstance();
        Database database = Controller.getInstance().getDatabaseInstance();
        TextualOption oStreetName = new TextualOption(TextualMenuContent.QUERY_STREET_NAME, new GetStringInputAction());
        oStreetName.displayOption();
        oStreetName.getAction().doAction();
        String streetName = inputHandler.getCapturedString();
        streetName = inputValidator.validateStreetName(streetName);
        ArrayList<Building> buildingsAtAddress = new ArrayList<Building>();
        ArrayList<Apartment> freeApartments = new ArrayList<Apartment>();
        for (Building building : database.getBuildings().values()) {
            if (building.getAddress().contentEquals(streetName)) {
                buildingsAtAddress.add(building);
            }
        }
        for (Building building : buildingsAtAddress) {
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
