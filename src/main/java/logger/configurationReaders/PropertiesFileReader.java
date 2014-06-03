package main.java.logger.configurationReaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * The Class PropertiesFileReader reads the configuration from a .properties file.
 */
public class PropertiesFileReader implements ConfigurationReader {

	/** The Constant FILE_SEPARATOR. */
	private static final String FILE_SEPARATOR = ";";
	
	
	@Override
	public Configuration readConfiguration(String configFilePath) throws FileNotFoundException {
		Configuration config = new Configuration();
		Properties properties = new Properties();
		FileInputStream configFile;
		try {
			configFile = new FileInputStream(configFilePath);
			properties.load(configFile);
			config.setLevel(this.getLevelPropertyValue(properties));
			config.setMessageFormat(this.getPropertyValue(properties, "messageFormat"));
			config.setMessageSeparator(this.getPropertyValue(properties, "messageSeparator"));
			config.setLogToFiles(this.getPropertyValue(properties, "logToFiles").split(FILE_SEPARATOR));
			config.setLogToConsole(this.getLogToConsolePropertyValue(properties));
			return config;
		} catch (IOException e) {
			System.err.println("There was an IOException when Loading Configuration: "+ e.getMessage());
			System.err.println("Check your Configuration file");
		}
		return null;
	}
	
	/**
	 * Gets the property value.
	 *
	 * @param properties the properties parsed
	 * @param key the property key
	 * @return the property value
	 */
	private String getPropertyValue(Properties properties, String key) {
		String value = properties.getProperty(key);
		if (value != null) {
			return value;
		}
		return "";
	}
	
	/**
	 * Gets the level property value.
	 *
	 * @param properties the properties parsed
	 * @return the level property value
	 */
	private String getLevelPropertyValue(Properties properties) {
		String value = this.getPropertyValue(properties, "level");
		if (value.isEmpty()) {
			value = "OFF";
		}
		return value;
	}
	
	/**
	 * Gets the log to console property value.
	 *
	 * @param properties the properties parsed
	 * @return the log to console property value
	 */
	private Boolean getLogToConsolePropertyValue(Properties properties) {
		String value = this.getPropertyValue(properties, "logToConsole");
		if (value.isEmpty()) {
			return false;
		}
		return Boolean.valueOf(value);
	}

}
