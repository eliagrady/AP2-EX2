/**
 * Represents an Option object,
 */
package view.menu;

public interface Option {
	/**
	 * Get the Action this option represents
	 *
	 * @return the Action associated with this option
	 */
	public Action getAction();

	/**
	 * Display this option to the user
	 */
	public void displayOption();

	/**
	 * Get the string representation of this option
	 *
	 * @return the string representation of this option
	 */
	public String getDisplayOption();
}
