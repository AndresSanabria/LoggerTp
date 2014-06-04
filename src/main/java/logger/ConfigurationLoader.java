package main.java.logger;

import java.io.IOException;

import main.java.logger.configurationReaders.Configuration;
import main.java.logger.configurationReaders.ConfigurationReader;
import main.java.logger.configurationReaders.PropertiesFileReader;
import main.java.logger.configurationReaders.XMLFileReader;

/**
 * The Class ConfigurationLoader load the Configuration of the Logger from the configuration file
 */
public class ConfigurationLoader {
	
	/** The Constant DISTANCE_CALLER_GIVE_FORMAT. */
	private static final Integer DISTANCE_CALLER_GIVE_FORMAT = 4;
	
	/** The Constant PROPERTIES_FILE_PATH. */
	private static final String PROPERTIES_FILE_PATH = "configFiles/config.properties";
	
	/** The Constant XML_FILE_PATH. */
	private static final String XML_FILE_PATH = "configFiles/config.xml";

	/** The configuration */
	private Configuration configuration;
	
	
	/**
	 * Instantiates a new configuration loader.
	 */
	public ConfigurationLoader() {
		this.loadConfiguration(new PropertiesFileReader(PROPERTIES_FILE_PATH));
		if (this.configuration == null) {
			this.loadConfiguration(new XMLFileReader(XML_FILE_PATH));
			if (this.configuration == null) {
				this.initializeByDefault();
			}
		}
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
	 * @param configReader the configuration reader
	 */
	private void loadConfiguration(ConfigurationReader configReader) {
		try {
			this.configuration = configReader.readConfiguration();
		} catch (IOException e) { }
	}
	
	/**
	 * Initializes configuration by default values.
	 */
	private void initializeByDefault() {
		this.configuration = new Configuration();
		this.configuration.setLevel("OFF");
		this.configuration.setMessageFormat("%p %n %m");
		this.configuration.setMessageSeparator("-");
		this.configuration.setLogToFiles(new String[] {"log.txt"});
		this.configuration.setLogToConsole(false);
	}
	
}
