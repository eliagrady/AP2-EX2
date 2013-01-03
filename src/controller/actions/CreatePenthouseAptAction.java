package controller.actions;

import controller.Controller;
import controller.InputHandler;
import controller.InputValidator;
import model.db.errors.ObjectCreationException;
import model.street.Building;
import model.street.Penthouse;
import view.menu.Action;
import view.menu.TextualOption;

/**
 * Created with IntelliJ IDEA.
 * User: Elia
 * Date: 12/29/12
 * Time: 6:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreatePenthouseAptAction implements Action {
	/**
	 * Do a general Action
	 */
	@Override
	public void doAction() {
		Controller controller = Controller.getInstance();
		InputHandler inputHandler = InputHandler.getInstance();
		InputValidator inputValidator = InputValidator.getInstance();
		String currentAddress = InputValidator.getInstance().getCurrentBuildingAddress();
		Building building = controller.getDatabaseInstance().getBuilding(currentAddress);
		TextualOption oApartmentFloor = new TextualOption("Set apartment's floor: ", new GetIntegerInputAction());
		oApartmentFloor.displayOption();
		oApartmentFloor.getAction().doAction();
		int floor = inputHandler.getCapturedInt();
		floor = inputValidator.validateFloor(floor);

		TextualOption oApartmentNumberOfRooms = new TextualOption("Set apartment's number of rooms: ", new GetIntegerInputAction());
		oApartmentNumberOfRooms.displayOption();
		oApartmentNumberOfRooms.getAction().doAction();
		int numOfRooms = inputHandler.getCapturedInt();
		numOfRooms = inputValidator.validateNumOfRooms(numOfRooms);

		TextualOption oApartmentArea = new TextualOption("Set apartment's area: ", new GetFloatInputAction());
		oApartmentArea.displayOption();
		oApartmentArea.getAction().doAction();
		float area = inputHandler.getCapturedFloat();
		area = inputValidator.validateArea(area);

		TextualOption oApartmentBalconyArea = new TextualOption("Set apartment's balcony area: ", new GetFloatInputAction());
		oApartmentBalconyArea.displayOption();
		oApartmentBalconyArea.getAction().doAction();
		float balconyArea = inputHandler.getCapturedFloat();
		balconyArea = inputValidator.validateAuxArea(balconyArea);

		Penthouse apartment;
		try {
			apartment = (Penthouse) controller.getFactory().createObject("Penthouse");
			apartment.setAddress(currentAddress);
			apartment.setArea(area);
			apartment.setFloor(floor);
			apartment.setNumOfRooms(numOfRooms);
			apartment.setResidentName("");//It's an empty apartment, ready to be sold
			apartment.setBalconyArea(balconyArea);
			controller.getDatabaseInstance().addApartment(apartment);
		} catch (ObjectCreationException e) {
			System.out.println(e.getMessage());
		}
	}
}
