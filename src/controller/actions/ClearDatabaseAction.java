package controller.actions;

import controller.Controller;
import controller.TextualMenuContent;
import model.street.Building;
import view.menu.Action;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Elia
 * Date: 12/29/12
 * Time: 4:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClearDatabaseAction implements Action {
	/**
	 * Do a general Action
	 */
	@Override
	public void doAction() {
		Controller.getInstance().getDatabaseInstance().updateNewState(new HashMap<String, Building>());
		System.out.println(TextualMenuContent.CLEAR_DATABASE_OPTION + " completed");
	}
}
