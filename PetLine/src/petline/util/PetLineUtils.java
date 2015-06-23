package petline.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PetLineUtils {

	public static String getURL() {
		String url = "";
		try {
			Configuration config = new PropertiesConfiguration("petline/config.properties");
			url = config.getString("URL");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		return url;
	}
	
}
