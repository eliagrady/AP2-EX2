/**
 * Ex 1
 * Author: Elia Grady
 * ID: 300907060
 * Course: Advanced Programming 1
 * T.A.: Igor Rochlin
 */
package model.street;

import java.io.Serializable;

/**
 * This class represents a penthouse apartment
 */
public class Penthouse extends Apartment implements Serializable {
	private double balconyArea;

	/**
	 * Default constructor
	 */
	public Penthouse() {
		super();
	}

	/**
	 * Returns the total area of the apartment including additional area, in square meters
	 *
	 * @return The total area of the apartment including additional area, in square meters
	 */
	@Override
	public double totalArea() {
		return (getArea() + getBalconyArea());
	}

	/**
	 * Returns a string representation of this apartment's type
	 *
	 * @return A string representation of this apartment's type
	 */
	@Override
	public String toString() {
		return "Penthouse apartment";
	}

	/**
	 * Creates a new penthouse apartment.
	 *
	 * @param residentName Name of the resident
	 * @param floor        On which floor is the apartment located at
	 * @param numOfRooms   How many rooms does this apartment has
	 * @param area         The total area for this apartment in square meters
	 * @param balconyArea  The total area for this apartment's balcony in square meters
	 */
	public Penthouse(String residentName, int floor, int numOfRooms, double area, double balconyArea) {
		super(residentName, floor, numOfRooms, area);
		this.balconyArea = balconyArea;
	}


	/**
	 * Returns the area of the balcony for this penthouse
	 *
	 * @return The area of the balcony for this penthouse
	 */
	public double getBalconyArea() {
		return balconyArea;
	}

	/**
	 * Set the area of the balcony for this penthouse
	 *
	 * @param balconyArea the new area of the balcony for this penthouse to set
	 */
	public void setBalconyArea(double balconyArea) {
		this.balconyArea = balconyArea;
	}

	/**
	 * Clones this Penthouse object
	 *
	 * @return a clone of this Penthouse object
	 * @throws CloneNotSupportedException iff the clone has failed
	 */
	@Override
	public Penthouse clone() throws CloneNotSupportedException {
		Penthouse clone = (Penthouse) super.clone();
		clone.setBalconyArea(this.balconyArea);
		return clone;
	}
}
