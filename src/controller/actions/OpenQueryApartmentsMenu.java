package controller.actions;

import controller.Controller;
import controller.TextualMenuContent;
import view.menu.Action;

/**
 * Created with IntelliJ IDEA.
 * User: Elia
 * Date: 12/29/12
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class OpenQueryApartmentsMenu implements Action {
	/**
	 * Do a general Action
	 */
	@Override
	public void doAction() {
		Controller.getInstance().changeMenu(TextualMenuContent.APARTMENTS_QUERY_OPTION);
	}
}
