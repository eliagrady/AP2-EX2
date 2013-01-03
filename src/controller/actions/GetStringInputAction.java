/**
 * Represents an action that captures a String to the input handler
 */
package controller.actions;

import controller.InputHandler;
import view.menu.Action;


public class GetStringInputAction implements Action {
	/**
	 * Default constructor
	 */
	public GetStringInputAction() {
	}

	/**
	 * Gets an string that is captured by the input handler
	 */
	@Override
	public void doAction() {
		try {
			InputHandler.getInstance().captureString();
		} catch (Exception e) {
			System.out.println("Unable to get input correctly");
			e.printStackTrace();
		}
	}
}
