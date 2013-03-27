/**
 * This class encapsulates SQL queries into methods relevant to this specific project
 */
package model.db;

public class MySqlQuery {
	public static String insertBuilding(int buildingID, String streetName, int streetNumber) {
		return "INSERT INTO buildings" +
			       "(buildingID,streetName,streetNumber) " +
			       "VALUES('" + buildingID + "','" + streetName + "','" + streetNumber + "')";
	}

	public static String insertApartment(int buildingID, int apartmentID, String residentName, int floor, int numOfRooms,
	                                     double area) {
		return "INSERT INTO apartments" +
			       "(buildingID,apartmentID,residentName,floor,numOfRooms,area) " +
			       "VALUES('" + buildingID + "','" + apartmentID + "','" + residentName +
			       "','" + floor + "','" + numOfRooms + "','" + area + "')";
	}

	public static String insertRegularApartment(int apartmentID, float warehouseArea) {
		return "INSERT INTO regular_apartments" +
			       "(apartmentID,warehouseArea) " +
			       "VALUES('" + apartmentID + "','" + warehouseArea + "')";
	}

	public static String insertPenthouseApartment(int apartmentID, double balconyArea) {
		return "INSERT INTO penthouse_apartments" +
			       "(apartmentID,balconyArea) " +
			       "VALUES('" + apartmentID + "','" + balconyArea + "')";
	}

	public static String insertGardenApartment(int apartmentID, boolean separateEntrance, double gardenArea) {
		int separateEntranceValue = 0;
		if (separateEntrance) {
			separateEntranceValue = 1;
		}
		return "INSERT INTO garden_apartments" +
			       "(apartmentID,separateEntrance,gardenArea) " +
			       "VALUES('" + apartmentID + "','" + separateEntranceValue + "','" + gardenArea + "')";
	}

	public static String clearTable(String tableName) {
		return "TRUNCATE TABLE " + tableName;
	}

	public static String deleteTable(String tableName) {
		return "DROP TABLE " + tableName;
	}

	public static String requestBuildings() {
		return "SELECT * from buildings";
	}


	public static String requestApartments(int buildingID) {
		return "SELECT * from apartments WHERE buildingID=" + buildingID;
	}

	public static String requestRegularApartmentsData(int apartmentID) {
		return "SELECT * from regular_apartments WHERE apartmentID=" + apartmentID;
	}

	public static String requestPenthouseApartmentsData(int apartmentID) {
		return "SELECT * from penthouse_apartments WHERE apartmentID=" + apartmentID;
	}

	public static String requestGardenApartmentsData(int apartmentID) {
		return "SELECT * from garden_apartments WHERE apartmentID=" + apartmentID;
	}
}
