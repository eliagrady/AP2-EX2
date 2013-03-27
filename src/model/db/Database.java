/**
 * Represents a database objects, allowing to query, modify, save and restore all buildings and apartments inside.
 * Provides an API for actions this Database can perform.
 */
package model.db;

import model.street.Apartment;
import model.street.Building;

import java.util.HashMap;

public interface Database {
	/**
	 * Get the instance (eagerly created) of this Database
	 *
	 * @return the instance of the Database
	 */
	public Database getInstance();

	/**
	 * Updates a new status for all buildings (can be used to clone from another database or clearing this one)
	 *
	 * @param buildingsUpdated the new buildings data to update
	 */
	public void updateNewState(HashMap<String, Building> buildingsUpdated);

	/**
	 * Gets the current buildings this database has
	 *
	 * @return the buildings this database has
	 */
	public HashMap<String, Building> getBuildings();


	/*
	 * Get the building associated with a given address
	 *
	 * @param address the address of the building
	 * @return the building of which the address is his

	public Building getBuilding(String address) {
		return buildings.get(address);
	}

	*/

	/**
	 * Queries whether a building exist at a given address
	 *
	 * @param address the address of the building that is being queried.
	 * @return true iff a building exist at this address.
	 */
	public boolean isBuildingExist(String address);

	/**
	 * Restore the database from a 'database' file.
	 */
	@SuppressWarnings("unchecked")
	public void restoreState();

	/**
	 * Write the database to a 'database' file.
	 */
	public void saveState();

	/**
	 * Adds a new building to the database
	 *
	 * @param address  the address of the building (buildings has unique addresses)
	 * @param building the new building to add
	 */
	public void addBuilding(String address, Building building);

	/**
	 * Add a new apartment, giving that it already has an associated address
	 *
	 * @param apartment the apartment to add
	 */
	public void addApartment(Apartment apartment);

	/**
	 * Sell an apartment by setting a new resident name
	 *
	 * @param apartment    the apartment to sell
	 * @param residentName the new resident name
	 * @return true iff the sell was successfully completed
	 */
	public boolean sellApartment(Apartment apartment, String residentName);
}
