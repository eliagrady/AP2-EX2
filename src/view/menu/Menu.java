/**
 * Represents a Menu object, that has options
 */
package view.menu;

import view.menu.errors.InvalidSelectionException;

public interface Menu {
	/**
	 * Display this menu to the user
	 */
	public void showMenu();

	/**
	 * Call an option by it's number (Coded)
	 *
	 * @param option The selection number
	 * @return The option selected
	 */
	public Option getOption(int option) throws InvalidSelectionException;
}
