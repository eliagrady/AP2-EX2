/**
 * Represents a Textual query object, that prints incoming query result
 */
package view;

import controller.TextualMenuContent;
import model.street.Apartment;

import java.util.ArrayList;

public final class TextualApartmentsQuery {
	private static final TextualApartmentsQuery instance = new TextualApartmentsQuery();

	/**
	 * Get the instance (eagerly created) of this TextualApartmentsQuery
	 *
	 * @return the instance of the TextualApartmentsQuery
	 */
	public static TextualApartmentsQuery getInstance() {
		return instance;
	}

	public void showQuery(ArrayList<Apartment> results) {
		System.out.println(TextualMenuContent.LINE_SEPARATOR);
		if (results.size() == 0) {
			System.out.println(TextualMenuContent.QUERY_NO_RESULTS);
		} else {
			System.out.println(TextualMenuContent.QUERY_APT_MENU_TITLE);
		}
		for (Apartment apartment : results) {
			System.out.println(TextualMenuContent.LINE_SEPARATOR + "\n");
			System.out.println(apartment.getDescription());

		}
		System.out.println(TextualMenuContent.LINE_SEPARATOR + "\n");
	}
}
