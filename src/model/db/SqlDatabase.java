/**
 * Represents a database objects, allowing to query, modify, save and restore all buildings and apartments inside.
 * Provides an API for actions this Database can perform.
 */
package model.db;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import model.street.*;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SqlDatabase implements Database, Serializable {
	private static final Database instance = new SqlDatabase();
	private static HashMap<String, Building> buildings;
	private static MysqlDataSource ds = new MysqlConnectionPoolDataSource();


	/**
	 * Constructor for the SqlDatabase
	 */
	public SqlDatabase() {
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
	 * Restore the database from a remote or local MySQL server.
	 */
	@SuppressWarnings("unchecked")
	public void restoreState() {
		Connection connection = null;
		try {
			assertDatabase();
			ds.setServerName(MySqlSettings.databaseHost);
			ds.setDatabaseName(MySqlSettings.databaseName);
			connection = ds.getConnection(MySqlSettings.adminUsername, MySqlSettings.adminPassword);
			Statement buildingStatement = connection.createStatement();
			Statement apartmentStatement = connection.createStatement();
			Statement specialStatement = connection.createStatement();
			ResultSet buildingResultSet = null;
			ResultSet apartmentResultSet = null;
			ResultSet specialApartmentResultSet = null;
			try {
				buildingResultSet = buildingStatement.executeQuery(MySqlQuery.requestBuildings());
				while (buildingResultSet.next()) {
					int buildingID = buildingResultSet.getInt(MySqlSettings.BUILDING_ID);
					String streetName = buildingResultSet.getString(MySqlSettings.STREET_NAME);
					int streetNumber = buildingResultSet.getInt(MySqlSettings.STREET_NUMBER);
					apartmentResultSet = apartmentStatement.executeQuery(MySqlQuery.requestApartments(buildingID));
					ArrayList<Apartment> apartments = new ArrayList<Apartment>();
					String address = streetName + " " + streetNumber;
					while (apartmentResultSet.next()) {
						String residentName = apartmentResultSet.getString(MySqlSettings.RESIDENT_NAME);
						int floor = apartmentResultSet.getInt(MySqlSettings.FLOOR);
						int numOfRooms = apartmentResultSet.getInt(MySqlSettings.NUM_OF_ROOMS);
						double area = apartmentResultSet.getDouble(MySqlSettings.AREA);
						int apartmentID = apartmentResultSet.getInt(MySqlSettings.APARTMENT_ID);
						//retrieve special data
						try {
							specialApartmentResultSet = specialStatement.executeQuery(MySqlQuery.requestGardenApartmentsData(apartmentID));
							specialApartmentResultSet.next();
							int separateEntrance = specialApartmentResultSet.getInt(MySqlSettings.SEPARATE_ENTRANCE);
							int gardenArea = specialApartmentResultSet.getInt(MySqlSettings.GARDEN_AREA);
							boolean hasSeparateEntrance = false;
							if (separateEntrance > 0) {
								hasSeparateEntrance = true;
							}
							GardenApartment apartment = new GardenApartment(residentName, numOfRooms, area, gardenArea, hasSeparateEntrance);
							apartment.setAddress(address);
							apartments.add(apartment);
						} catch (Exception e1) {

							//Not this one..
						} finally {
							specialApartmentResultSet.close();
						}
						try {
							specialApartmentResultSet = specialStatement.executeQuery(MySqlQuery.requestPenthouseApartmentsData(apartmentID));
							specialApartmentResultSet.next();
							double balconyArea = specialApartmentResultSet.getDouble(MySqlSettings.BALCONY_AREA);
							Penthouse apartment = new Penthouse(residentName, floor, numOfRooms, area, balconyArea);
							apartment.setAddress(address);
							apartments.add(apartment);
						} catch (Exception e1) {

							//Not this one..
						} finally {
							specialApartmentResultSet.close();
						}
						try {
							specialApartmentResultSet = specialStatement.executeQuery(MySqlQuery.requestRegularApartmentsData(apartmentID));
							specialApartmentResultSet.next();
							float warehouseArea = specialApartmentResultSet.getFloat(MySqlSettings.WAREHOUSE_AREA);
							RegularApartment apartment = new RegularApartment(residentName, floor, numOfRooms, area);
							apartment.setWarehouseArea(warehouseArea);
							apartment.setAddress(address);
							apartments.add(apartment);
						} catch (Exception e) {

							//Not this one.. wait... there aren't any more =[
						} finally {
							specialApartmentResultSet.close();
						}
					}
					addBuilding(address, new Building(streetName, streetNumber, apartments));
					apartmentResultSet.close();
				}
				buildingResultSet.close();
			} catch (SQLException e) {//Apartment try
				e.printStackTrace();
			}
		} catch (SQLException e) {//Building try
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void assertDatabase() {
		try {
			Class.forName(MySqlSettings.DRIVER).newInstance();
			Connection connectionTest = DriverManager.getConnection
				                                          ("jdbc:mysql://" + MySqlSettings.databaseHost + "/" +
					                                           "?user=" + MySqlSettings.adminUsername + "" +
					                                           "&password=" + MySqlSettings.adminPassword + "");
			Statement statementTest = connectionTest.createStatement();
			int Result = statementTest.executeUpdate(MySqlQuery.createDatabaseQuery());


			ds.setServerName(MySqlSettings.databaseHost);
			ds.setDatabaseName(MySqlSettings.databaseName);
			ds.setUser(MySqlSettings.adminUsername);
			ds.setPassword(MySqlSettings.adminPassword);
			Connection connection = null;
			Statement statement = null;
			connection = ds.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(MySqlQuery.createDatabaseQuery());

			statement = connection.createStatement();
			statement.executeUpdate(MySqlQuery.createApartmentTableQuery());
			statement = connection.createStatement();
			statement.executeUpdate(MySqlQuery.createBuildingsTableQuery());
			statement = connection.createStatement();
			statement.executeUpdate(MySqlQuery.createRegularApartmentTableQuery());
			statement = connection.createStatement();
			statement.executeUpdate(MySqlQuery.createPenthouseApartmentTableQuery());
			statement = connection.createStatement();
			statement.executeUpdate(MySqlQuery.createGardenApartmentTableQuery());
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write the database to a MySQL database.
	 */
	public void saveState() {
		Connection connection = null;
		try {
			assertDatabase();
			clearDatabase();
			ds.setServerName(MySqlSettings.databaseHost);
			ds.setDatabaseName(MySqlSettings.databaseName);
			connection = ds.getConnection(MySqlSettings.adminUsername, MySqlSettings.adminPassword);
			Statement statement = connection.createStatement();

			try {
				for (Building building : buildings.values()) {
					String streetName = building.getStreetName();
					int streetNumber = building.getStreetNumber();
					int buildingID = building.hashCode();
					statement.execute(MySqlQuery.insertBuilding(buildingID, streetName, streetNumber));

					//Same for all apartments in the building
					String address = building.getAddress();
					try {
						for (Apartment apartment : building.getApartments()) {
							//Joint variables
							String residentName = apartment.getResidentName();
							int floor = apartment.getFloor();
							int numOfRooms = apartment.getNumOfRooms();
							double area = apartment.getArea();
							int apartmentID = apartment.hashCode();
							statement.execute(MySqlQuery.insertApartment(buildingID, apartmentID, residentName, floor, numOfRooms, area));

							if (apartment instanceof RegularApartment) {
								RegularApartment regularApartment = (RegularApartment) apartment;
								float warehouseArea = regularApartment.getWarehouseArea();
								statement.execute(MySqlQuery.insertRegularApartment(apartmentID, warehouseArea));
							} else if (apartment instanceof Penthouse) {
								Penthouse penthouse = (Penthouse) apartment;
								double balconyArea = penthouse.getBalconyArea();
								statement.execute(MySqlQuery.insertPenthouseApartment(apartmentID, balconyArea));
							} else if (apartment instanceof GardenApartment) {
								GardenApartment gardenApartment = (GardenApartment) apartment;
								boolean separateEntrance = gardenApartment.isSeparateEntrance();
								double gardenArea = gardenApartment.getGardenArea();
								statement.execute(MySqlQuery.insertGardenApartment(apartmentID, separateEntrance, gardenArea));
							}
						}
					} catch (MySQLIntegrityConstraintViolationException e) {
						//Skipped: found duplicate entry!
					}

				}
			} catch (MySQLIntegrityConstraintViolationException e) {
				//Skipped: found duplicate entry!
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Clears the entire database of internal records
	 */
	private void clearDatabase() {
		ds.setServerName(MySqlSettings.databaseHost);
		ds.setDatabaseName(MySqlSettings.databaseName);
		Connection connection = null;
		try {
			connection = ds.getConnection(MySqlSettings.adminUsername, MySqlSettings.adminPassword);
		} catch (SQLException e) {
		}
		Statement statement = null;
		if (connection != null) {
			try {
				statement = connection.createStatement();

				try {
					statement.execute(MySqlQuery.clearTable(MySqlSettings.TABLE_BUILDINGS));
				} catch (SQLException e) {
				}
				try {
					statement.execute(MySqlQuery.clearTable(MySqlSettings.TABLE_APARTMENTS));
				} catch (SQLException e) {
				}
				try {
					statement.execute(MySqlQuery.clearTable(MySqlSettings.TABLE_REGULAR_APARTMENTS));
				} catch (SQLException e) {
				}
				try {
					statement.execute(MySqlQuery.clearTable(MySqlSettings.TABLE_PENTHOUSE_APARTMENTS));
				} catch (SQLException e) {
				}
				try {
					statement.execute(MySqlQuery.clearTable(MySqlSettings.TABLE_GARDEN_APARTMENTS));
				} catch (SQLException e) {
				}
			} catch (SQLException e) {
			}
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
