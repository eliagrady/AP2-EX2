/**
 * Represent a safe execution of an 'exit' from this program.
 */
package controller.actions;

import controller.Controller;
import controller.TextualMenuContent;
import view.menu.Action;

public class QuitAction implements Action {
	/**
	 * Default constructor
	 */
	public QuitAction() {
	}

	/**
	 * Quit the program
	 */
	@Override
	public void doAction() {
		System.out.println(TextualMenuContent.GOODBYE);
		Controller.getInstance().changeMenu(TextualMenuContent.QUIT);
	}
}
