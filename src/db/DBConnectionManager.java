package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionManager {

	private DBConnectionManager() {}

	private static Connection connection;
	
	private static String driverClass = "com.mysql.jdbc.Driver";
	private static String connURL = "jdbc:mysql://localhost:3306/biblioteka_fakulteta";
	
	private static String user = "root";
	private static String password = "root";
	
	public static Connection getConnection() {
		try {
			if (connection == null) {
				Class.forName(driverClass);
				connection = DriverManager.getConnection(connURL, user, password);
			}
		} catch (Exception ex) {ex.printStackTrace();}

		return connection;
	}

	public static void closeConnection() {
		try {
			connection.close();
			connection = null;
		} catch (Exception ex) {ex.printStackTrace();}
	}
}
