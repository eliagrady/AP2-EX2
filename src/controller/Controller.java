/**
 * Represents a controller that is responsible for the logic of the program run sequence,
 * and also communicates with both the model and view to create and display it's commands.
 */
package controller;

import controller.actions.*;
import model.db.Database;
import model.db.MyPrototypeFactory;
import model.street.Building;
import model.street.GardenApartment;
import model.street.Penthouse;
import model.street.RegularApartment;
import view.menu.Option;
import view.menu.TextualMenu;
import view.menu.TextualOption;
import view.menu.errors.InvalidSelectionException;

import java.util.ArrayList;

public class Controller {
	private static final Controller instance = new Controller();
	private Database database;
	private MyPrototypeFactory objectsFactory;
	private String menuCodeRequest;

	/**
	 * Default constructor
	 */
	private Controller() {
		this.database = new Database();
	}

	/**
	 * Get the instance (eagerly created) of this Controller
	 *
	 * @return the instance of the controller
	 */
	public static Controller getInstance() {
		return instance;
	}

	public void changeMenu(String option) {
		this.menuCodeRequest = option; // USE WITH CAUTION
	}

	/**
	 * Get the database this controller has
	 *
	 * @return the database this controller has
	 */
	public Database getDatabaseInstance() {
		return database.getInstance();
	}

	/**
	 * Updates the database this controller has
	 *
	 * @param updateDatabase the updated database for this controller to set
	 */
	public void updateDatabase(Database updateDatabase) {
		this.database = updateDatabase;
	}

	/**
	 * Get the prototype factory this controller has
	 *
	 * @return the prototype factory this controller has
	 */
	public MyPrototypeFactory getFactory() {
		return this.objectsFactory;
	}

	/**
	 * Creates a new prototype factory
	 *
	 * @return the generated prototype factory
	 */
	private MyPrototypeFactory generateFactory() {
		MyPrototypeFactory objectsFactory = new MyPrototypeFactory();
		objectsFactory.addPrototypeObject("Regular", new RegularApartment());
		objectsFactory.addPrototypeObject("Penthouse", new Penthouse());
		objectsFactory.addPrototypeObject("Garden", new GardenApartment());
		objectsFactory.addPrototypeObject("Building", new Building());
		return objectsFactory;
	}

	/**
	 * The main sequence for this program.
	 * First, creates a ground, then robots, and finally shapes.
	 * afterwards, a simulation can be run.
	 */
	protected void mainSequence() {
		InputHandler inputHandler = InputHandler.getInstance();
		objectsFactory = generateFactory();
		// Creating the menus
		//createGround menu;
		/*
		ArrayList<Option> oCreateBuilding = new ArrayList<Option>(3);
		oCreateBuilding.add(new TextualOption(TextualMenuContent.CREATE_BUILDING_OPTION, new CreateBuildingAction()));
		oCreateBuilding.add(new TextualOption(TextualMenuContent.MAIN_MENU_OPTION, new ReturnToMainMenuAction()));
		oCreateBuilding.add(new TextualOption(TextualMenuContent.QUIT, new QuitAction()));
		TextualMenu createBuilding = new TextualMenu(TextualMenuContent.CREATE_BUILDING_TITLE, oCreateBuilding);
		*/

		//queryApartments menu
		ArrayList<Option> oQueryApartments = new ArrayList<Option>(11);
		oQueryApartments.add(new TextualOption(TextualMenuContent.APARTMENTS_QUERY_OPTION1, new QueryFreeRegAptAction()));
		oQueryApartments.add(new TextualOption(TextualMenuContent.APARTMENTS_QUERY_OPTION2, new QueryFreeRegAptRoomsAction()));
		oQueryApartments.add(new TextualOption(TextualMenuContent.APARTMENTS_QUERY_OPTION3, new QueryFreeRegAptRoomsAddressAction()));
		oQueryApartments.add(new TextualOption(TextualMenuContent.APARTMENTS_QUERY_OPTION4, new QueryRegAptAddressAction()));
		oQueryApartments.add(new TextualOption(TextualMenuContent.APARTMENTS_QUERY_OPTION5, new QueryRegAptAction()));
		oQueryApartments.add(new TextualOption(TextualMenuContent.APARTMENTS_QUERY_OPTION6, new QueryPenthouseAptAction()));
		oQueryApartments.add(new TextualOption(TextualMenuContent.APARTMENTS_QUERY_OPTION7, new QueryGardenAptAction()));
		oQueryApartments.add(new TextualOption(TextualMenuContent.APARTMENTS_QUERY_OPTION8, new QueryFreeAptAction()));
		oQueryApartments.add(new TextualOption(TextualMenuContent.APARTMENTS_QUERY_OPTION9, new QueryFreeAptAddressAction()));

		oQueryApartments.add(new TextualOption(TextualMenuContent.MAIN_MENU_OPTION, new ReturnToMainMenuAction()));
		oQueryApartments.add(new TextualOption(TextualMenuContent.QUIT, new QuitAction()));
		TextualMenu queryApartments = new TextualMenu(TextualMenuContent.APARTMENTS_QUERY_TITLE, oQueryApartments);
		/*
		//Sell menu, to choose filtering parameters
		ArrayList<Option> oSellApartments = new ArrayList<Option>(3);
		oSellApartments.add(new TextualOption(TextualMenuContent.APARTMENT_SELL_OPTION, new SellAptAction()));
		oSellApartments.add(new TextualOption(TextualMenuContent.QUIT, new QuitAction()));
		TextualMenu sellApartment = new TextualMenu(TextualMenuContent.APARTMENT_SELL_TITLE, oSellApartments);
		*/
		//Main menu
		ArrayList<Option> oMainMenu = new ArrayList<Option>(4);
		oMainMenu.add(new TextualOption(TextualMenuContent.CREATE_BUILDING_OPTION, new CreateBuildingAction()));
		oMainMenu.add(new TextualOption(TextualMenuContent.APARTMENTS_QUERY_OPTION, new OpenQueryApartmentsMenu()));
		oMainMenu.add(new TextualOption(TextualMenuContent.APARTMENT_SELL_OPTION, new SellAptAction()));
		oMainMenu.add(new TextualOption(TextualMenuContent.CLEAR_DATABASE_OPTION, new ClearDatabaseAction()));
		oMainMenu.add(new TextualOption(TextualMenuContent.QUIT, new QuitAction()));
		TextualMenu mainMenu = new TextualMenu(TextualMenuContent.MAIN_MENU_TITLE, oMainMenu);

		//Finished creating menus

		//Run the program, starting with the Main Menu
		TextualMenu currentMenu = mainMenu;
		menuCodeRequest = TextualMenuContent.MAIN_MENU_OPTION;
		boolean validInput = false;
		do {
			do {
				currentMenu.showMenu();
				//Menu display : show current menu
				InputHandler.getInstance().captureInt();
				int selection = inputHandler.getCapturedInt();
				try {
					currentMenu.getOption(selection).getAction().doAction();
					validInput = true;
					if (menuCodeRequest.contentEquals(TextualMenuContent.APARTMENTS_QUERY_OPTION)) {
						currentMenu = queryApartments;
					} else if (menuCodeRequest.contentEquals(TextualMenuContent.RETURN_TO_MAIN_MENU)) {
						currentMenu = mainMenu;
					}

				} catch (InvalidSelectionException e) {
					System.out.println(e.getMessage());
					validInput = false;
				} catch (Throwable e) {
					getDatabaseInstance().saveState(); //Whoa!
				}
			}
			while (!validInput);
		} while (!menuCodeRequest.contentEquals(TextualMenuContent.QUIT));
		getDatabaseInstance().saveState();
	}
}
