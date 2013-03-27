/**
 * This class represents a settings class, for constants used in the SQL representation.
 * If a column's name in the representation needs to change, it can be changed here alone.
 */
package model.db;

public class MySqlSettings {
	// Settings

	public static final String databaseURL = "localhost";
	public static final String databaseName = "ap2-ex1-db";
	protected static final String adminUsername = "root";
	protected static final String adminPassword = "";

	// Data representation within the SQL model.

	//Table names
	public static final String TABLE_BUILDINGS = "buildings";
	public static final String TABLE_APARTMENTS = "apartments";
	public static final String TABLE_REGULAR_APARTMENTS = "regular_apartments";
	public static final String TABLE_PENTHOUSE_APARTMENTS = "penthouse_apartments";
	public static final String TABLE_GARDEN_APARTMENTS = "garden_apartments";


	// Column names within the tables

	public static final String BUILDING_ID = "buildingID";
	public static final String STREET_NAME = "streetName";
	public static final String STREET_NUMBER = "streetNumber";
	public static final String RESIDENT_NAME = "residentName";
	public static final String FLOOR = "floor";
	public static final String NUM_OF_ROOMS = "numOfRooms";
	public static final String AREA = "area";
	public static final String APARTMENT_ID = "apartmentID";

	public static final String SEPARATE_ENTRANCE = "separateEntrance";
	public static final String GARDEN_AREA = "gardenArea";

	public static final String BALCONY_AREA = "balconyArea";

	public static final String WAREHOUSE_AREA = "warehouseArea";

}
