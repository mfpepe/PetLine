package box.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class BoxUtils {

	public static String getURL() {
		String url = "";
		try {
			Configuration config = new PropertiesConfiguration("box/config.properties");
			url = config.getString("URL");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		return url;
	}
	
	public static String getURLWS() {
		String url = "";
		try {
			Configuration config = new PropertiesConfiguration("box/config.properties");
			url = config.getString("URLWS");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		return url;
	}	
	
}
