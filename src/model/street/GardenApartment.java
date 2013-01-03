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
 * This class represents a garden apartment
 */
public class GardenApartment extends Apartment implements Serializable {
	private boolean separateEntrance;
	private double gardenArea;

	public GardenApartment() {
		super();
		setFloor(1);//always on the first floor
	}

	/**
	 * Returns the total area of the apartment including additional area, in square meters
	 *
	 * @return The total area of the apartment including additional area, in square meters
	 */
	@Override
	public double totalArea() {
		return (getArea() + getGardenArea());
	}

	/**
	 * Returns a string representation of this apartment's type
	 *
	 * @return A string representation of this apartment's type
	 */
	@Override
	public String toString() {
		return "Garden apartment";
	}

	/**
	 * Creates a new garden apartment apartment.
	 *
	 * @param residentName     Name of the resident
	 * @param numOfRooms       How many rooms does this apartment has
	 * @param area             The total area for this apartment in square meters
	 * @param gardenArea       The total area for this apartment's garden in square meters
	 * @param separateEntrance Whether this apartment has a separate entrance.
	 */
	public GardenApartment(
		                      String residentName, int numOfRooms, double area, double gardenArea, boolean separateEntrance) {
		super(residentName, 1, numOfRooms, area);        //A garden apt. is always on the first floor
		this.separateEntrance = separateEntrance;
	}

	/**
	 * Returns true iff the apartment has a separate entrance
	 *
	 * @return If the apartment has a separate entrance
	 */
	public boolean isSeparateEntrance() {
		return separateEntrance;
	}

	/**
	 * Sets whether this garden apartment has a separate entrance
	 *
	 * @param separateEntrance True iff this garden apartment has a separate entrance
	 */
	public void setSeparateEntrance(boolean separateEntrance) {
		this.separateEntrance = separateEntrance;
	}

	/**
	 * Returns the garden area for this garden apartment
	 *
	 * @return The garden area for this garden apartment
	 */
	public double getGardenArea() {
		return gardenArea;
	}

	/**
	 * Sets a new garden area for this garden apartment
	 *
	 * @param gardenArea The new garden area for this garden apartment to set
	 */
	public void setGardenArea(double gardenArea) {
		this.gardenArea = gardenArea;
	}

	/**
	 * Clones this GardenApartment object
	 *
	 * @return a clone of this GardenApartment object
	 * @throws CloneNotSupportedException iff the clone has failed
	 */
	@Override
	public GardenApartment clone() throws CloneNotSupportedException {
		GardenApartment clone = (GardenApartment) super.clone();
		clone.gardenArea = this.gardenArea;
		clone.separateEntrance = this.separateEntrance;
		clone.setFloor(1);
		return clone;
	}
}
