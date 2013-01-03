/**
 * Represents a textual output 'interface' by wrapping the way to display textual information.
 */
package view;

public class TextualConsole {
	private static final TextualConsole instance = new TextualConsole();

	/**
	 * Get the instance (eagerly created) of this TextualConsole
	 *
	 * @return the instance of the TextualConsole
	 */
	public static TextualConsole getInstance() {
		return instance;
	}

	public void println(String line) {
		System.out.println(line);
	}

	public void print(String line) {
		System.out.print(line);
	}
}
