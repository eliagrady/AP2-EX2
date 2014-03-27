/**
 * Represents an action that creates a new Garden Apartment
 */
package controller.actions;

import controller.Controller;
import controller.InputHandler;
import controller.InputValidator;
import controller.TextualMenuContent;
import model.db.errors.ObjectCreationException;
import model.street.GardenApartment;
import view.menu.Action;
import view.menu.TextualOption;

public class CreateGardenAptAction implements Action {
    /**
     * Perform Action: create a new garden apartment that will be placed in the
     * current building address held by the InputValidator.
     */
    @Override
    public void doAction() {
        Controller controller = Controller.getInstance();
        InputHandler inputHandler = InputHandler.getInstance();
        InputValidator inputValidator = InputValidator.getInstance();
        String currentAddress = InputValidator.getInstance().getCurrentBuildingAddress();

        TextualOption oApartmentNumber = new TextualOption(TextualMenuContent.CREATE_APT_REQUEST_APT_NUM, new GetIntegerInputAction());
        oApartmentNumber.displayOption();
        oApartmentNumber.getAction().doAction();
        int apartmentNumber = inputHandler.getCapturedInt();
        apartmentNumber = inputValidator.validateApartmentNum(apartmentNumber);

        TextualOption oApartmentNumberOfRooms = new TextualOption(TextualMenuContent.CREATE_APT_REQUEST_NUM_OF_ROOMS, new GetIntegerInputAction());
        oApartmentNumberOfRooms.displayOption();
        oApartmentNumberOfRooms.getAction().doAction();
        int numOfRooms = inputHandler.getCapturedInt();
        numOfRooms = inputValidator.validateNumOfRooms(numOfRooms);

        TextualOption oApartmentArea = new TextualOption(TextualMenuContent.CREATE_APT_REQUEST_AREA, new GetFloatInputAction());
        oApartmentArea.displayOption();
        oApartmentArea.getAction().doAction();
        float area = inputHandler.getCapturedFloat();
        area = inputValidator.validateArea(area);

        TextualOption oApartmentGardenArea = new TextualOption(TextualMenuContent.CREATE_APT_REQUEST_GARDEN_AREA, new GetFloatInputAction());
        oApartmentGardenArea.displayOption();
        oApartmentGardenArea.getAction().doAction();
        float gardenArea = inputHandler.getCapturedFloat();
        gardenArea = inputValidator.validateAuxArea(gardenArea);

        TextualOption oApartmentSeparateEntrance = new TextualOption(TextualMenuContent.CREATE_APT_REQUEST_SEPARATE_ENTRANCE, new GetStringInputAction());
        oApartmentSeparateEntrance.displayOption();
        oApartmentSeparateEntrance.getAction().doAction();
        String hasPrivateEntrance = inputHandler.getCapturedString();
        hasPrivateEntrance = inputValidator.validatePrivateEntrance(hasPrivateEntrance);

        GardenApartment apartment;
        try {
            apartment = (GardenApartment) controller.getFactory().createObject("Garden");
            apartment.setApartmentNumber(apartmentNumber);
            apartment.setAddress(currentAddress);
            apartment.setArea(area);
            apartment.setFloor(1); //Garden apartments always on the first floor
            apartment.setNumOfRooms(numOfRooms);
            apartment.setResidentName("");//It's an empty apartment, ready to be sold
            apartment.setGardenArea(gardenArea);
            if (hasPrivateEntrance.equalsIgnoreCase("y")) {
                apartment.setSeparateEntrance(true);
            } else {
                apartment.setSeparateEntrance(false);
            }
            controller.getDatabaseInstance().addApartment(apartment);
        } catch (ObjectCreationException e) {
            System.out.println(e.getMessage());
        }
    }
}
