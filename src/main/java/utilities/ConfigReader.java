package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties properties = new Properties();
	private static String browser;

	static {
		try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (inputStream == null) {
				throw new IOException("Unable to find config.properties file in the classpath");
			}
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Get a property from config file
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	// Set a property in the properties object
	public static void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	// Get a property with priority: TestContext > config file
	public static String getTestProperty(String key) {
		if ("browser".equalsIgnoreCase(key) && browser != null) {
			return browser; // Prioritize the browser set from the testng.xml file
		}
		return properties.getProperty(key);
	}

	// Set test-specific properties (e.g., browser)
	public static void setTestProperty(String key, String value) {
		if ("browser".equalsIgnoreCase(key)) {
			browser = value;
		} else {
			properties.setProperty(key, value);
		}
	}

	// Get the browser value
	public static String getBrowser() {
		return browser;
	}

	// Set the browser value
	public static void setBrowser(String browserValue) {
		browser = browserValue;
	}

}
