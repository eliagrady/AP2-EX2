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
 * This class represents a regular apartment
 */
public class RegularApartment extends Apartment implements Serializable {
	private float warehouseArea;

	/**
	 * Default constructor
	 */
	public RegularApartment() {
		super();
	}

	/**
	 * Creates a regular apartment.
	 *
	 * @param residentName Name of the resident
	 * @param floor        On which floor is the apartment located at
	 * @param numOfRooms   How many rooms does this apartment has
	 * @param area         The total area for this apartment in square meters
	 */
	public RegularApartment(String residentName, int floor, int numOfRooms, double area) {
		super(residentName, floor, numOfRooms, area);
	}

	/**
	 * Returns the total area of the apartment including additional area, in square meters
	 *
	 * @return The total area of the apartment including additional area, in square meters
	 */
	@Override
	public double totalArea() {
		return (this.getArea() + this.warehouseArea);
	}

	/**
	 * Returns a string representation of this apartment's type
	 *
	 * @return A string representation of this apartment's type
	 */
	@Override
	public String toString() {
		return "Regular apartment";
	}


	/**
	 * Getter for the warehouse Area for this apartment
	 *
	 * @return the warehouse area for this apartment
	 */
	public float getWarehouseArea() {
		return warehouseArea;
	}

	/**
	 * Set a new warehouse area for this apartment
	 *
	 * @param warehouseArea the new warehouse area to set
	 */
	public void setWarehouseArea(float warehouseArea) {
		this.warehouseArea = this.warehouseArea;
	}
}
