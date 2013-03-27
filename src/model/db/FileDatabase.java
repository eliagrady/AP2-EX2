/**
 * Represents a database objects, allowing to query, modify, save and restore all buildings and apartments inside.
 * Provides an API for actions this Database can perform.
 */
package model.db;

import model.street.Apartment;
import model.street.Building;

import java.io.*;
import java.util.HashMap;

public class FileDatabase implements Database, Serializable {
	private static final Database instance = new FileDatabase();
	private HashMap<String, Building> buildings;
	private String databaseFilename = "database.tmp";

	public FileDatabase() {
		if (buildings == null) {
			buildings = new HashMap<String, Building>();
		}
	}

	/**
	 * Get the instance (eagerly created) of this Database
	 *
	 * @return the instance of the Database
	 */
	public Database getInstance() {
		return instance;
	}

	/**
	 * Updates a new status for all buildings (can be used to clone from another database or clearing this one)
	 *
	 * @param buildingsUpdated the new buildings data to update
	 */
	public void updateNewState(HashMap<String, Building> buildingsUpdated) {
		this.buildings = buildingsUpdated;
	}

	/**
	 * Gets the current buildings this database has
	 *
	 * @return the buildings this database has
	 */
	public HashMap<String, Building> getBuildings() {
		return this.buildings;
	}


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
	public boolean isBuildingExist(String address) {
		return this.buildings.containsKey(address);
	}

	/**
	 * Restore the database from a 'database' file.
	 */
	@SuppressWarnings("unchecked")
	public void restoreState() {
		FileInputStream fis = null;
		HashMap<String, Building> restoredBuildings = null;
		try {
			File file = new File(databaseFilename);
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found, creating new");
			File file = new File(databaseFilename);
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
				System.out.println("O snap, something went wrong and I can't find the save file");
				e1.printStackTrace();
			}
		}
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (ois != null) {
			try {
				restoredBuildings = (HashMap<String, Building>) ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}
		if (restoredBuildings != null) {
			this.buildings = restoredBuildings;
			System.out.println("restored successfully!");
		}
		try {
			if (ois != null) ois.close();
			if (fis != null) fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write the database to a 'database' file.
	 */
	public void saveState() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(databaseFilename);
		} catch (FileNotFoundException e) {
			File file = new File(databaseFilename);
			try {
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e1) {
				System.out.println("Oops, something went wrong and the save file couldn't be created");
				e1.printStackTrace();
			}
		}
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (oos != null) {
			try {
				oos.writeObject(this.buildings);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			if (oos != null) oos.close();
			if (fos != null) fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a new building to the database
	 *
	 * @param address  the address of the building (buildings has unique addresses)
	 * @param building the new building to add
	 */
	public void addBuilding(String address, Building building) {
		this.buildings.put(address, building);
	}

	/**
	 * Add a new apartment, giving that it already has an associated address
	 *
	 * @param apartment the apartment to add
	 */
	public void addApartment(Apartment apartment) {
		//
		if (this.isBuildingExist(apartment.getAddress())) {
			this.buildings.get(apartment.getAddress()).addApartment(apartment);
		}
	}

	/**
	 * Sell an apartment by setting a new resident name
	 *
	 * @param apartment    the apartment to sell
	 * @param residentName the new resident name
	 * @return true iff the sell was successfully completed
	 */
	public boolean sellApartment(Apartment apartment, String residentName) {
		return buildings.get(apartment.getAddress()).updateResidentName(apartment, residentName);
	}
}
