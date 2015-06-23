package petline.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.configuration.PropertiesConfiguration;



public class ConnectionManager {
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() {
		try {
			PropertiesConfiguration config = new PropertiesConfiguration("petline/config.properties");
			Class.forName(config.getString("driver-class")).newInstance();
			return DriverManager.getConnection(config.getString("driver-url"),config.getString("driver-url-user"),config.getString("driver-url-pass"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
