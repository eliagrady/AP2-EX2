package controller.actions;

import model.street.Apartment;
import view.menu.TextualOption;

/**
 * Created with IntelliJ IDEA.
 * User: Elia
 * Date: 1/3/13
 * Time: 8:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextualSellOption extends TextualOption {
	private Apartment apartment;

	public TextualSellOption(Apartment apartment, String description) {
		super(description, null);
		this.apartment = apartment;
	}

	public Apartment getApartment() {
		return apartment;
	}

}
