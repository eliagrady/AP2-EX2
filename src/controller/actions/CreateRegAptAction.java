/**
 * Represents creating a Circle shape action.
 */

package controller.actions;

import controller.Controller;
import controller.InputHandler;
import controller.InputValidator;
import model.db.errors.ObjectCreationException;
import model.street.Building;
import model.street.RegularApartment;
import view.menu.Action;
import view.menu.TextualOption;

public class CreateRegAptAction implements Action {
	/**
	 * Default contractor
	 */
	public CreateRegAptAction() {
	}

	/**
	 * This action creates a regular apartment and add it to the 'Database' instance
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

		TextualOption oApartmentWarehouseArea = new TextualOption("Set apartment's warehouse area: ", new GetFloatInputAction());
		oApartmentWarehouseArea.displayOption();
		oApartmentWarehouseArea.getAction().doAction();
		float warehouseArea = inputHandler.getCapturedFloat();
		warehouseArea = inputValidator.validateAuxArea(warehouseArea);

		RegularApartment apartment;
		try {
			apartment = (RegularApartment) controller.getFactory().createObject("Regular");
			apartment.setAddress(currentAddress);
			apartment.setArea(area);
			apartment.setFloor(floor);
			apartment.setNumOfRooms(numOfRooms);
			apartment.setResidentName("");//It's an empty apartment, ready to be sold
			apartment.setWarehouseArea(warehouseArea);
			controller.getDatabaseInstance().addApartment(apartment);
		} catch (ObjectCreationException e) {
			System.out.println(e.getMessage());
		}
	}
}
