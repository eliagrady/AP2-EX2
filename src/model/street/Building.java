/**
 * Ex 1
 * Author: Elia Grady
 * ID: 300907060
 * Course: Advanced Programming 1
 * T.A.: Igor Rochlin
 */
package model.street;

import model.db.AbstractPrototype;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents a regular building
 */
public class Building extends AbstractPrototype implements Serializable {
	private String streetName;
	private int streetNumber;
	private ArrayList<Apartment> apartments = new ArrayList<Apartment>();

	/**
	 * Default constructor
	 */
	public Building() {
	}

	public Building(String streetName, int streetNumber, ArrayList<Apartment> apartments) {
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.apartments = apartments;
	}

	/**
	 * Called after creating a new building to make sure the apartments has an updated address
	 */
	public void updateApartmentsAddress() {
		String address = streetName + " " + streetNumber;
		for (Apartment apartment : apartments) {//all apartments in building has the same address
			apartment.setAddress(address);
		}
	}

	/**
	 * Returns the street name of this building
	 *
	 * @return The street name of this building
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * Sets a new street name for this building
	 *
	 * @param streetName the new street name for this building
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * Returns the street number for this building
	 *
	 * @return The street number for this building
	 */
	public int getStreetNumber() {
		return streetNumber;
	}

	/**
	 * Sets a new street number for this building //ex: building no. 44 is now 44a
	 *
	 * @param streetNumber the street number to update
	 */
	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	/**
	 * Returns how many penthouses are in this building
	 *
	 * @return The number of penthouses in this building
	 */
	public int numOfPenthouses() {
		int total = 0;
		for (Apartment apt : apartments) {
			if (apt instanceof Penthouse) {
				total++;
			}
		}
		return total;
	}

	/**
	 * Returns the array of the apartments
	 *
	 * @return The apartments in this building represented in an array
	 */
	public ArrayList<Apartment> getApartments() {
		return this.apartments;
	}

	/**
	 * Sets a new array of the apartments
	 *
	 * @param apartments The new array of apartments for this building
	 */
	public void setApartments(ArrayList<Apartment> apartments) {
		this.apartments = apartments;
	}

	/**
	 * Adds a new apartment in this building
	 *
	 * @param apartment The new apartment for this building
	 */
	public void addApartment(Apartment apartment) {
		this.apartments.add(apartment);
	}

	/**
	 * Returns how many apartments are in this building
	 *
	 * @return The number of apartments in this building
	 */
	public int totalNumOfApartments() {
		return this.apartments.size();
	}

	/**
	 * Returns the total area of gardens in a building in square meters
	 *
	 * @return The total area of gardens in a building in square meters
	 */
	public int totalAreaOfGardens() {
		int total = 0;
		for (Apartment apt : apartments) {
			if (apt instanceof GardenApartment) {
				total += ((GardenApartment) apt).getGardenArea();
			}
		}
		return total;
	}

	/**
	 * Returns the number of empty apartments in the building
	 *
	 * @return The number of empty apartments in the building
	 */
	public int numOfEmptyApartments() {
		int total = 0;
		for (Apartment apt : apartments) {
			if (apt.getResidentName() == null || apt.getResidentName().contentEquals("")) {//null or empty resident name
				total++;
			}
		}
		return total;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(this.streetNumber);
		str.append(" ");
		str.append(this.streetName);
		str.append(" st. has the following apartments:\n");
		for (Apartment apt : apartments) {
			str.append(apt.toString());
			str.append(" on floor: ");
			str.append(apt.getFloor());
			str.append(" with total area of ");
			str.append(apt.totalArea());

			str.append("\n");
		}
		return str.toString();
	}

	public String getAddress() {
		return this.getStreetName() + " " + this.streetNumber;
	}

	/**
	 * Clones this Building object
	 *
	 * @return a clone of this Building object
	 * @throws CloneNotSupportedException iff the clone has failed
	 */
	@Override
	public Building clone() throws CloneNotSupportedException {
		Building clone = (Building) super.clone();
		clone.streetNumber = this.streetNumber;
		clone.streetName = this.streetName;
		clone.apartments = new ArrayList<Apartment>();
		return clone;
	}

	/**
	 * Selling an apartment by traversing over the apartments, and changing the name of the resident
	 *
	 * @param apartmentToUpdate the apartment to set the new resident name
	 * @param residentName      the new resident name to set
	 * @return true iff the apartment was found and updated with the new resident name.
	 */
	public boolean updateResidentName(Apartment apartmentToUpdate, String residentName) {
		for (Apartment apartment : this.apartments) {
			if (apartment.equals(apartmentToUpdate)) {
				apartment.setResidentName(residentName);
				return true;
			}
		}
		return false;
	}

	public void setAddress(String address) {
		String[] addressParts = address.split(" ");
		int streetNumber = Integer.valueOf(addressParts[addressParts.length - 1].trim());
		setStreetNumber(streetNumber);
		String streetName = "";
		for (String addressPart : addressParts) {
			streetName += addressPart;
		}
		setStreetName(streetName);
	}
}
