/**
 * Represents an action for creating a building.
 */
package controller.actions;

import controller.Controller;
import controller.InputHandler;
import controller.InputValidator;
import controller.TextualMenuContent;
import model.db.DatabaseXmlParser;
import model.db.MyPrototypeFactory;
import model.street.Building;
import view.menu.Action;
import view.menu.TextualOption;

public class ParseBuildingsXmlAction implements Action {
    /**
     * Default constructor
     */
    public ParseBuildingsXmlAction() {

    }

    /**
     * This action creates a new Building and updates the 'database' instance with the newly generated one
     */
    @Override
    public void doAction() {
        try {
            Controller controller = Controller.getInstance();
            MyPrototypeFactory objectsFactory = Controller.getInstance().getFactory();
            InputHandler inputHandler = InputHandler.getInstance();
            InputValidator inputValidator = InputValidator.getInstance();
            Building building = null;
            TextualOption oFilename = new TextualOption(TextualMenuContent.BUILD_REQUEST_XML_FILE_NAME, new GetStringInputAction());
            oFilename.displayOption();
            oFilename.getAction().doAction();
            String filename = inputHandler.getCapturedString();
            filename = inputValidator.validateFileName(filename);
            //DEBUG
            //filename = "D:\\Dropbox\\CS\\Workspace\\AP2-Ex2\\inputTargil2.xml";
            if (filename != null) {
                DatabaseXmlParser parser = new DatabaseXmlParser();
                parser.parse(filename);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }
}
