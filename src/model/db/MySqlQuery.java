/**
 * This class encapsulates SQL queries into methods relevant to this specific project
 * These methods returns the Query string needed to manipulate the SQL requests
 */
package model.db;

public class MySqlQuery {
	/**
	 * Generates a query that insert a new building into the database
	 *
	 * @param buildingID   the building ID to be inserted
	 * @param streetName   the street name to be inserted
	 * @param streetNumber the street number to be inserted
	 * @return a string representation of the query needed to insert a new building
	 */
	public static String insertBuilding(int buildingID, String streetName, int streetNumber) {
		return "INSERT INTO " + MySqlSettings.TABLE_BUILDINGS +
			       "(" + MySqlSettings.BUILDING_ID + "," + MySqlSettings.STREET_NAME + "," + MySqlSettings.STREET_NUMBER + ") " +
			       "VALUES('" + buildingID + "','" + streetName + "','" + streetNumber + "')";
	}

	/**
	 * Generates a query that insert a new apartment into the database
	 *
	 * @param buildingID   the building that this apartment belongs to
	 * @param apartmentID  the apartment ID to be inserted
	 * @param residentName the resident name to be inserted
	 * @param floor        the floor of the  apartment
	 * @param numOfRooms   the number of rooms in this apartment
	 * @param area         the area of the apartment
	 * @return a string representation of the query needed to insert a new apartment
	 */
	public static String insertApartment(int buildingID, int apartmentID, String residentName, int floor, int numOfRooms,
	                                     double area) {
		return "INSERT INTO " + MySqlSettings.TABLE_APARTMENTS +
			       "(" + MySqlSettings.BUILDING_ID + "," + MySqlSettings.APARTMENT_ID + "," + MySqlSettings.RESIDENT_NAME + "" +
			       "," + MySqlSettings.FLOOR + "," + MySqlSettings.NUM_OF_ROOMS + "," + MySqlSettings.AREA + ") " +
			       "VALUES('" + buildingID + "','" + apartmentID + "','" + residentName +
			       "','" + floor + "','" + numOfRooms + "','" + area + "')";
	}

	/**
	 * Generates a query that insert a new regular apartment (special information) into the database
	 *
	 * @param apartmentID   the apartment ID to be inserted
	 * @param warehouseArea the warehouse area for this apartment
	 * @return a string representation of the query needed to insert a new regular apartment special information
	 */
	public static String insertRegularApartment(int apartmentID, float warehouseArea) {
		return "INSERT INTO " + MySqlSettings.TABLE_REGULAR_APARTMENTS + "" +
			       "(" + MySqlSettings.APARTMENT_ID + "," + MySqlSettings.WAREHOUSE_AREA + ") " +
			       "VALUES('" + apartmentID + "','" + warehouseArea + "')";
	}

	/**
	 * Generates a query that insert a new penthouse apartment (special information) into the database
	 *
	 * @param apartmentID the apartment ID to be inserted
	 * @param balconyArea the balcony area for this apartment
	 * @return a string representation of the query needed to insert a new penthouse apartment special information
	 */
	public static String insertPenthouseApartment(int apartmentID, double balconyArea) {
		return "INSERT INTO " + MySqlSettings.TABLE_PENTHOUSE_APARTMENTS + "" +
			       "(" + MySqlSettings.APARTMENT_ID + "," + MySqlSettings.BALCONY_AREA + ") " +
			       "VALUES('" + apartmentID + "','" + balconyArea + "')";
	}

	/**
	 * Generates a query that insert a new garden apartment (special information) into the database
	 *
	 * @param apartmentID      the apartment ID to be inserted
	 * @param separateEntrance whether this garden apartment has a separate entrance
	 * @param gardenArea       the area of this garden apartment's garden
	 * @return a string representation of the query needed to insert a new garden apartment special information
	 */
	public static String insertGardenApartment(int apartmentID, boolean separateEntrance, double gardenArea) {
		int separateEntranceValue = 0;
		if (separateEntrance) {
			separateEntranceValue = 1;
		}
		return "INSERT INTO " + MySqlSettings.TABLE_GARDEN_APARTMENTS + "" +
			       "(" + MySqlSettings.APARTMENT_ID + "," + MySqlSettings.SEPARATE_ENTRANCE + "," + MySqlSettings.GARDEN_AREA + ") " +
			       "VALUES('" + apartmentID + "','" + separateEntranceValue + "','" + gardenArea + "')";
	}

	/**
	 * Generates a query that clears a specific table (TRUNCATE, not DROP!)
	 *
	 * @param tableName the name of the table to be cleared
	 * @return a string representation of the query needed to activate this request
	 */
	public static String clearTable(String tableName) {
		return "TRUNCATE TABLE " + tableName;
	}

	/**
	 * Generates a query that REMOVES a specific table (DROP, not TRUNCATE!)
	 *
	 * @param tableName the name of the table to be removed
	 * @return a string representation of the query needed to  to activate this request
	 */
	public static String deleteTable(String tableName) {
		return "DROP TABLE " + tableName;
	}

	/**
	 * Generates a query to get all buildings
	 *
	 * @return a string representation of the query needed to get all buildings
	 */
	public static String requestBuildings() {
		return "SELECT * from " + MySqlSettings.TABLE_BUILDINGS;
	}

	/**
	 * Generates a query to get all apartments
	 *
	 * @param buildingID the building's ID to look for
	 * @return a string representation of the query needed to get all apartments
	 */
	public static String requestApartments(int buildingID) {
		return "SELECT * from " + MySqlSettings.TABLE_APARTMENTS + " WHERE " + MySqlSettings.BUILDING_ID + "=" + buildingID;
	}

	/**
	 * Generates a query to get all regular apartments data
	 *
	 * @param apartmentID the apartment's ID to look for
	 * @return a string representation of the query needed to get all regular apartment's special data
	 */
	public static String requestRegularApartmentsData(int apartmentID) {
		return "SELECT * from " + MySqlSettings.TABLE_REGULAR_APARTMENTS + " WHERE " + MySqlSettings.APARTMENT_ID + "=" + apartmentID;
	}

	/**
	 * Generates a query to get all penthouse apartments data
	 *
	 * @param apartmentID the apartment's ID to look for
	 * @return a string representation of the query needed to get all penthouse apartment's special data
	 */
	public static String requestPenthouseApartmentsData(int apartmentID) {
		return "SELECT * from " + MySqlSettings.TABLE_PENTHOUSE_APARTMENTS + " WHERE " + MySqlSettings.APARTMENT_ID + "=" + apartmentID;
	}

	/**
	 * Generates a query to get all garden apartments data
	 *
	 * @param apartmentID the apartment's ID to look for
	 * @return a string representation of the query needed to get all garden apartment's special data
	 */
	public static String requestGardenApartmentsData(int apartmentID) {
		return "SELECT * from " + MySqlSettings.TABLE_GARDEN_APARTMENTS + " WHERE " + MySqlSettings.APARTMENT_ID + "=" + apartmentID;
	}

	/**
	 * Returns a query to create a database
	 *
	 * @return a query to create a database
	 */
	public static String createDatabaseQuery() {
		return "CREATE DATABASE IF NOT EXISTS " + MySqlSettings.databaseName + " DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci";
	}

	/**
	 * Returns a query to create a new apartment table
	 *
	 * @return a query to create a new apartment table
	 */
	public static String createApartmentTableQuery() {
		return "CREATE TABLE IF NOT EXISTS `" + MySqlSettings.TABLE_APARTMENTS + "` (" +
			       "  `" + MySqlSettings.BUILDING_ID + "` int(11) NOT NULL," +
			       "  `" + MySqlSettings.APARTMENT_ID + "` int(11) NOT NULL," +
			       "  `" + MySqlSettings.RESIDENT_NAME + "` text CHARACTER SET latin1 NOT NULL," +
			       "  `" + MySqlSettings.FLOOR + "` int(11) NOT NULL," +
			       "  `" + MySqlSettings.NUM_OF_ROOMS + "` int(11) NOT NULL," +
			       "  `" + MySqlSettings.AREA + "` double NOT NULL," +
			       "  PRIMARY KEY (`" + MySqlSettings.APARTMENT_ID + "`)" +
			       ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
	}

	/**
	 * Returns a query to create a new buildings table
	 *
	 * @return a query to create a new buildings table
	 */
	public static String createBuildingsTableQuery() {
		return "CREATE TABLE IF NOT EXISTS `" + MySqlSettings.TABLE_BUILDINGS + "` (" +
			       "  `" + MySqlSettings.BUILDING_ID + "` int(11) NOT NULL," +
			       "  `" + MySqlSettings.STREET_NAME + "` varchar(255) COLLATE utf8_unicode_ci NOT NULL," +
			       "  `" + MySqlSettings.STREET_NUMBER + "` int(11) NOT NULL," +
			       "  PRIMARY KEY (`" + MySqlSettings.BUILDING_ID + "`)," +
			       "  UNIQUE KEY `" + MySqlSettings.BUILDING_ID + "` (`" + MySqlSettings.BUILDING_ID + "`)" +
			       ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
	}

	/**
	 * Returns a query to create a new regular apartment table
	 *
	 * @return a query to create a new regular apartment table
	 */
	public static String createRegularApartmentTableQuery() {
		return "CREATE TABLE IF NOT EXISTS `" + MySqlSettings.TABLE_REGULAR_APARTMENTS + "` (" +
			       "  `" + MySqlSettings.APARTMENT_ID + "` int(11) NOT NULL," +
			       "  `" + MySqlSettings.WAREHOUSE_AREA + "` float NOT NULL," +
			       "  PRIMARY KEY (`" + MySqlSettings.APARTMENT_ID + "`)," +
			       "  UNIQUE KEY `" + MySqlSettings.APARTMENT_ID + "` (`" + MySqlSettings.APARTMENT_ID + "`)" +
			       ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
	}

	/**
	 * Returns a query to create a new penthouse apartment table
	 *
	 * @return a query to create a new penthouse apartment table
	 */
	public static String createPenthouseApartmentTableQuery() {
		return "CREATE TABLE IF NOT EXISTS `" + MySqlSettings.TABLE_PENTHOUSE_APARTMENTS + "` (" +
			       "  `" + MySqlSettings.APARTMENT_ID + "` int(11) NOT NULL," +
			       "  `" + MySqlSettings.BALCONY_AREA + "` double NOT NULL," +
			       "  PRIMARY KEY (`" + MySqlSettings.APARTMENT_ID + "`)," +
			       "  UNIQUE KEY `" + MySqlSettings.APARTMENT_ID + "` (`" + MySqlSettings.APARTMENT_ID + "`)" +
			       ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
	}

	/**
	 * Returns a query to create a new garden apartment table
	 *
	 * @return a query to create a new garden apartment table
	 */
	public static String createGardenApartmentTableQuery() {
		return "CREATE TABLE IF NOT EXISTS `" + MySqlSettings.TABLE_GARDEN_APARTMENTS + "` (" +
			       "  `" + MySqlSettings.APARTMENT_ID + "` int(11) NOT NULL," +
			       "  `" + MySqlSettings.SEPARATE_ENTRANCE + "` tinyint(1) NOT NULL," +
			       "  `" + MySqlSettings.GARDEN_AREA + "` double NOT NULL," +
			       "  PRIMARY KEY (`" + MySqlSettings.APARTMENT_ID + "`)," +
			       "  UNIQUE KEY `" + MySqlSettings.APARTMENT_ID + "` (`" + MySqlSettings.APARTMENT_ID + "`)" +
			       ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
	}

	/**
	 * Returns an array of queries that creates a new database, based on the predefined settings
	 *
	 * @return an array of queries that creates a new database
	 */
	public static String[] getQueriesForDatabaseCreation() {    //INIT database if missing
		String[] queries = new String[8];
		queries[0] = "CREATE DATABASE IF NOT EXISTS " + MySqlSettings.databaseName + " DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci";
		queries[1] = "CREATE TABLE IF NOT EXISTS `" + MySqlSettings.TABLE_APARTMENTS + "` (" +
			             "  `" + MySqlSettings.BUILDING_ID + "` int(11) NOT NULL," +
			             "  `" + MySqlSettings.APARTMENT_ID + "` int(11) NOT NULL," +
			             "  `" + MySqlSettings.RESIDENT_NAME + "` text CHARACTER SET latin1 NOT NULL," +
			             "  `" + MySqlSettings.FLOOR + "` int(11) NOT NULL," +
			             "  `" + MySqlSettings.NUM_OF_ROOMS + "` int(11) NOT NULL," +
			             "  `" + MySqlSettings.AREA + "` double NOT NULL," +
			             "  PRIMARY KEY (`" + MySqlSettings.APARTMENT_ID + "`)" +
			             ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
		queries[2] = "CREATE TABLE IF NOT EXISTS `" + MySqlSettings.TABLE_BUILDINGS + "` (" +
			             "  `" + MySqlSettings.BUILDING_ID + "` int(11) NOT NULL," +
			             "  `" + MySqlSettings.STREET_NAME + "` varchar(255) COLLATE utf8_unicode_ci NOT NULL," +
			             "  `" + MySqlSettings.STREET_NUMBER + "` int(11) NOT NULL," +
			             "  PRIMARY KEY (`" + MySqlSettings.BUILDING_ID + "`)," +
			             "  UNIQUE KEY `" + MySqlSettings.BUILDING_ID + "` (`" + MySqlSettings.BUILDING_ID + "`)" +
			             ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
		queries[3] = "CREATE TABLE IF NOT EXISTS `" + MySqlSettings.TABLE_GARDEN_APARTMENTS + "` (" +
			             "  `" + MySqlSettings.APARTMENT_ID + "` int(11) NOT NULL," +
			             "  `" + MySqlSettings.SEPARATE_ENTRANCE + "` tinyint(1) NOT NULL," +
			             "  `" + MySqlSettings.GARDEN_AREA + "` double NOT NULL," +
			             "  PRIMARY KEY (`" + MySqlSettings.APARTMENT_ID + "`)," +
			             "  UNIQUE KEY `" + MySqlSettings.APARTMENT_ID + "` (`" + MySqlSettings.APARTMENT_ID + "`)" +
			             ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
		queries[4] = "CREATE TABLE IF NOT EXISTS `" + MySqlSettings.TABLE_PENTHOUSE_APARTMENTS + "` (" +
			             "  `" + MySqlSettings.APARTMENT_ID + "` int(11) NOT NULL," +
			             "  `" + MySqlSettings.BALCONY_AREA + "` double NOT NULL," +
			             "  PRIMARY KEY (`" + MySqlSettings.APARTMENT_ID + "`)," +
			             "  UNIQUE KEY `" + MySqlSettings.APARTMENT_ID + "` (`" + MySqlSettings.APARTMENT_ID + "`)" +
			             ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
		queries[5] = "CREATE TABLE IF NOT EXISTS `" + MySqlSettings.TABLE_REGULAR_APARTMENTS + "` (" +
			             "  `" + MySqlSettings.APARTMENT_ID + "` int(11) NOT NULL," +
			             "  `" + MySqlSettings.WAREHOUSE_AREA + "` float NOT NULL," +
			             "  PRIMARY KEY (`" + MySqlSettings.APARTMENT_ID + "`)," +
			             "  UNIQUE KEY `" + MySqlSettings.APARTMENT_ID + "` (`" + MySqlSettings.APARTMENT_ID + "`)" +
			             ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
		queries[6] = "SET SQL_MODE=\"NO_AUTO_VALUE_ON_ZERO\"";
		queries[7] = "SET time_zone = \"+00:00\"";
		return queries;
	}
}
