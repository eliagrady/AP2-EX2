/**
 * Represents a textual option, that has an action associated with itself.
 */
package view.menu;


public class TextualOption implements Option {
	Action action;
	String description;

	/**
	 * Default constructor
	 */
	public TextualOption() {
	}

	/**
	 * Creates a Textual Option object
	 *
	 * @param description the option's string representation
	 * @param action      the action this option represents
	 */
	public TextualOption(String description, Action action) {
		this.description = description;
		this.action = action;
	}

	/**
	 * Sets a new Option description
	 *
	 * @param description the new description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets a new Action for this option
	 *
	 * @param action the new action to set
	 */
	public void setAction(Action action) {
		this.action = action;
	}

	/**
	 * Get the Action for this option
	 *
	 * @return the action this option represents
	 */
	@Override
	public Action getAction() {
		return action;
	}

	/**
	 * Display this option's description
	 */
	@Override
	public void displayOption() {
		System.out.println(this.description);
	}

	/**
	 * Get the description this option represents
	 *
	 * @return the string representation of the description
	 */
	public String getDisplayOption() {
		return this.description;
	}
}
