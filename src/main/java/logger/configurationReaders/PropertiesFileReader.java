package logger.configurationReaders;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The Class PropertiesFileReader reads the configuration from a .properties file.
 */
public class PropertiesFileReader implements ConfigurationReader {

	/** The Constant FILE_SEPARATOR. */
	private static final String FILE_SEPARATOR = ";";

	/** The file path. */
	private String filePath;
	
	
	/**
	 * Instantiates a new PropertiesFileReader.
	 * 
	 * @param filePath the path to the file to be read
	 */
	public PropertiesFileReader(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public Configuration readConfiguration() throws IOException {
		Configuration config = new Configuration();
		Properties properties = new Properties();
		FileInputStream configFile;
		configFile = new FileInputStream(this.filePath);
		properties.load(configFile);
		config.setLevel(this.getLevelPropertyValue(properties));
		config.setMessageFormat(this.getPropertyValue(properties, "messageFormat"));
		config.setMessageSeparator(this.getPropertyValue(properties, "messageSeparator"));
		config.setLogToFiles(this.getPropertyValue(properties, "logToFiles").split(FILE_SEPARATOR));
		config.setLogToConsole(this.getLogToConsolePropertyValue(properties));
		return config;
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
