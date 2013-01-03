/**
 * This class represents a Textual menu object.
 */
package view.menu;

import view.menu.errors.InvalidSelectionException;

import java.util.ArrayList;

public class TextualMenu implements Menu {
	private String menuTitle;
	private ArrayList<Option> options;

	/**
	 * Creates a new Textual menu, that has a title and options to select from
	 *
	 * @param menuTitle the title of this menu
	 * @param options   the options for this menu
	 */
	public TextualMenu(String menuTitle, ArrayList<Option> options) {
		setOptions(options);
		setMenuTitle(menuTitle);
	}

	/**
	 * Set a new title for this menu
	 *
	 * @param menuTitle the new title to set
	 */
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	/**
	 * Set a new options list for this menu
	 *
	 * @param options an ArrayList holding the options for this menu
	 */
	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}

	/**
	 * Outputs this menu's textual representation, by printing the title and options in consecutive lines
	 */
	@Override
	public void showMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append(menuTitle);
		sb.append("\n");
		int i = 0;
		while (i < options.size()) {
			sb.append(i);
			sb.append(":  ");
			sb.append(options.get(i).getDisplayOption());
			sb.append("\n");
			i++;
		}
		System.out.println(sb);
	}

	/**
	 * Get the option based on it's number in the options list
	 *
	 * @param option The selection number
	 * @return the Option object selected
	 * @throws InvalidSelectionException if selection number is invalid
	 */
	@Override
	public Option getOption(int option) throws InvalidSelectionException {
		Option grabbedOption;
		try {
			grabbedOption = options.get(option);
		} catch (Exception e) {
			throw new InvalidSelectionException();
		}
		return grabbedOption;
	}
}
