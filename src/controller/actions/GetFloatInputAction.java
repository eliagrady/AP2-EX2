/**
 * Represents an action that captures a Float to the input handler
 */
package controller.actions;

import controller.InputHandler;
import view.menu.Action;


public class GetFloatInputAction implements Action {
	/**
	 * Default constructor
	 */
	public GetFloatInputAction() {
	}

	/**
	 * Gets a float that is captured by the input handler
	 */
	@Override
	public void doAction() {
		try {
			InputHandler.getInstance().captureFloat();
		} catch (Exception e) {
			System.out.println("Unable to get input correctly");
			e.printStackTrace();
		}
	}
}
