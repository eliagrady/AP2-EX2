/**
 * Represents an action to clear the current database being saved. (overrun it)
 */
package controller.actions;

import controller.Controller;
import controller.TextualMenuContent;
import model.street.Building;
import view.menu.Action;

import java.util.HashMap;

public class ClearDatabaseAction implements Action {
	/**
	 * Clears the current database
	 */
	@Override
	public void doAction() {
		Controller.getInstance().getDatabaseInstance().updateNewState(new HashMap<String, Building>());
		System.out.println(TextualMenuContent.CLEAR_DATABASE_OPTION + " completed");
	}
}
