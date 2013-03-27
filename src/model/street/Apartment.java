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

/**
 * This class represents an abstract apartment
 */
public abstract class Apartment extends AbstractPrototype implements Comparable<Apartment>, Serializable {
	private String address;
	private String residentName;
	private Integer floor;
	private int numOfRooms; // Integer representation, a 3.51 rooms apartment isn't legal
	private double area; //Apartment surface in square meters

	/**
	 * Default constructor
	 */
	public Apartment() {
	}

	/**
	 * Creates a regular apartment.
	 *
	 * @param residentName Name of the resident
	 * @param floor        On which floor is the apartment located at
	 * @param numOfRooms   How many rooms does this apartment has
	 * @param area         The total area for this apartment in square meters
	 */
	public Apartment(String residentName, int floor, int numOfRooms, double area) {
		this.residentName = residentName;
		this.floor = floor;
		this.numOfRooms = numOfRooms;
		this.area = area;
	}

	/**
	 * Returns the total area of the apartment including additional area, in square meters
	 *
	 * @return The total area of the apartment including additional area, in square meters
	 */
	public abstract double totalArea();

	/**
	 * Returns the resident's name
	 *
	 * @return The resident's name, or an empty string if the apartment is empty
	 */
	public String getResidentName() {
		return residentName;
	}

	/**
	 * Set a new resident's name
	 *
	 * @param residentName The new residents name to set
	 */
	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}

	/**
	 * Return the apartment's floor number
	 *
	 * @return The apartment's floor number
	 */
	public int getFloor() {
		return this.floor;
	}

	/**
	 * Set the apartment's floor number
	 *
	 * @param floor The apartment's floor number tp set
	 */
	public void setFloor(int floor) {
		if (this.floor == null) { //only set the floor once.
			this.floor = floor;
		}
	}

	/**
	 * Returns the number of rooms in the apartment
	 *
	 * @return The number of rooms in the apartment
	 */
	public int getNumOfRooms() {
		return numOfRooms;
	}

	/**
	 * Set the number of rooms in the apartment
	 *
	 * @param numOfRooms The new number of rooms in the apartment
	 */
	public void setNumOfRooms(int numOfRooms) {
		this.numOfRooms = numOfRooms;
	}

	/**
	 * Returns the area of the apartment in square meters
	 *
	 * @return The area of the apartment in square meters
	 */
	public double getArea() {
		return this.area;
	}

	/**
	 * Sets the area of the apartment in square meters
	 *
	 * @param area The area of the apartment in square meters to set
	 */
	public void setArea(double area) {
		this.area = area;
	}

	/**
	 * Compares this Apartment with the specified Apartment object for order by area.
	 *
	 * @param apartment The apartments to compare to
	 * @return Returns a negative integer, zero, or a positive integer as this object is less than,
	 *         equal to, or greater than the specified object.
	 */
	public int compareTo(Apartment apartment) {
		if (this.totalArea() < apartment.totalArea()) return -1;
		if (this.totalArea() > apartment.totalArea()) return 1;
		return 0;
	}

	/**
	 * Compares this Apartment with the specified Apartment object for order by area.
	 *
	 * @param apartment The apartments to compare to
	 * @return Returns true iff the area of both apartments
	 */
	public boolean equals(Apartment apartment) {
		return (apartment != null) && (this.totalArea() == apartment.totalArea());
	}

	/**
	 * Queries about this apartment's availability
	 *
	 * @return Returns true iff this apartment is empty
	 */
	public boolean isFree() {
		return !(this.residentName != null && this.residentName.length() != 0);
	}

	/**
	 * Called after the apartment was created
	 *
	 * @return the address of this apartment, or null if it is yet in a building
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Allows to set the address for this apartment (in creation time or if there is a modification)
	 *
	 * @param address the new address to set (Format: streetName + space + streetNumber
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get a short description of this apartment, comprised of:
	 * street name and number, type of apartment, number of rooms, and total area.
	 *
	 * @return a string representation of the description
	 */
	public String getDescription() {
		return "Address: " + getAddress() + "\n Type: " + this.toString() +
			       "\n Number of rooms: " + numOfRooms + "\n Total area:" + totalArea() + "\n";
	}
}
