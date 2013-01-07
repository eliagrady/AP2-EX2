/**
 * Represent an action that cause the return to the upper, main menu
 */

package controller.actions;

import controller.Controller;
import controller.TextualMenuContent;
import view.menu.Action;

public class ReturnToMainMenuAction implements Action {
	/**
	 * Default constructor
	 */
	public ReturnToMainMenuAction() {
	}

	/**
	 * Creates robots and update the ground with these changes
	 */
	@Override
	public void doAction() {
		Controller.getInstance().changeMenu(TextualMenuContent.RETURN_TO_MAIN_MENU);
	}
}
