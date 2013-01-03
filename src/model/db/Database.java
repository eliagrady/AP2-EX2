package model.db;

import model.street.Apartment;
import model.street.Building;

import java.io.*;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Elia
 * Date: 12/28/12
 * Time: 5:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Database implements Serializable {
	private static final Database instance = new Database();
	private HashMap<String, Building> buildings;

	public Database() {
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

	public Building getBuilding(String address) {
		return buildings.get(address);
	}

	public void updateNewState(HashMap<String, Building> buildingsUpdated) {
		this.buildings = buildingsUpdated;
	}

	//TODO should I use it?
	public HashMap<String, Building> getBuildings() {
		return this.buildings;
	}

	public boolean isBuildingExist(String address) {
		if (this.buildings.containsKey(address)) {
			return true;
		}
		return false;
	}

	public void restoreState() {
		FileInputStream fis = null;
		HashMap<String, Building> restoredBuildings = null;
		try {
			File file = new File("database.tmp");
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found, creating new");
			File file = new File("database.tmp");
			try {
				fis = new FileInputStream("database.tmp");
			} catch (FileNotFoundException e1) {
				System.out.println("O fuck, something went wrong and I can't find the save file");
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
			}
		}
		if (restoredBuildings != null) {
			this.buildings = restoredBuildings;
			System.out.println("restored successfully!");
		}
		try {
			ois.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveState() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("database.tmp");
		} catch (FileNotFoundException e) {
			File file = new File("database.tmp");
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
			oos.close();
			fos.close();
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
		if (buildings.get(apartment.getAddress()).updateResidentName(apartment, residentName)) {
			return true;
		}
		return false;
	}
}
