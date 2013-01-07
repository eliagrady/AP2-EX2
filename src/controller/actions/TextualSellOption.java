/**
 * Represents a textual sell option, relative to a specific apartment
 */
package controller.actions;

import model.street.Apartment;
import view.menu.TextualOption;

public class TextualSellOption extends TextualOption {
	private Apartment apartment;

	/**
	 * Construct a new Textual option for selling, by holding a specific apartment reference.
	 *
	 * @param apartment   the apartment to sell
	 * @param description the option's description
	 */
	public TextualSellOption(Apartment apartment, String description) {
		super(description, null);
		this.apartment = apartment;
	}

	/**
	 * Get the current apartment being sold
	 *
	 * @return the current apartment being sold
	 */
	public Apartment getApartment() {
		return apartment;
	}

}
