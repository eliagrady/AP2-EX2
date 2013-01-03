/**
 * Represents an action that captures a Integer to the input handler
 */
package controller.actions;

import controller.InputHandler;
import view.menu.Action;


public class GetIntegerInputAction implements Action {
	/**
	 * Default constructor
	 */
	public GetIntegerInputAction() {
	}

	/**
	 * Gets an integer that is captured by the input handler
	 */
	public void doAction() {
		try {
			InputHandler.getInstance().captureInt();
		} catch (Exception e) {
			System.out.println("Unable to get input correctly");
			e.printStackTrace();
		}
	}
}
