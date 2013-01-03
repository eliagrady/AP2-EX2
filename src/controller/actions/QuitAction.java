/**
 * Represent a rapid, unsafe execution of an 'exit' from this program.
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
	 * Quit the program after saving
	 */
	@Override
	public void doAction() {
		//Controller.getInstance().getDatabaseInstance().saveState();//save changes before exit
		System.out.println(TextualMenuContent.GOODBYE);
		Controller.getInstance().changeMenu(TextualMenuContent.QUIT);
	}
}
