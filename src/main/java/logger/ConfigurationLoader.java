package logger;

import java.io.FileNotFoundException;

import logger.configurationReaders.Configuration;
import logger.configurationReaders.PropertiesFileReader;

/**
 * The Class ConfigurationLoader load the Configuration of the Logger from the configuration file
 */
public class ConfigurationLoader {
	
	/** The Constant DISTANCE_CALLER_GIVE_FORMAT. */
	private static final Integer DISTANCE_CALLER_GIVE_FORMAT = 4;
	
	/** The Constant PROPERTIES_FILE_PATH. */
	private static final String PROPERTIES_FILE_PATH = "configFiles/config.properties";

	/** The configuration */
	private Configuration configuration;
	
	
	/**
	 * Instantiates a new configuration loader.
	 *
	 * @param configFilePath the configuration file path
	 */
	public ConfigurationLoader() {
		this.loadConfiguration();
	}
	
	/**
	 * Initialize formatter.
	 *
	 * @return the formatter
	 */
	public Formatter initializeFormatter() {
		Formatter formatter;
		String format = this.getConfiguration().getMessageFormat();
		String separator = this.getConfiguration().getMessageSeparator();
		if (separator != null && !separator.isEmpty()) {
			formatter = new SimpleFormatter(format, DISTANCE_CALLER_GIVE_FORMAT, separator);
		} else {
			formatter = new SimpleFormatter(format, DISTANCE_CALLER_GIVE_FORMAT);
		}
		return formatter;
	}

	/**
	 * Gets the configuration.
	 *
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		return this.configuration;
	}
	
	/**
	 * Load configuration.
	 *
	 * @param configFilePath the configuration file path
	 */
	private void loadConfiguration() {
		try {
			PropertiesFileReader configReader = new PropertiesFileReader();
			this.configuration = configReader.readConfiguration(PROPERTIES_FILE_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
