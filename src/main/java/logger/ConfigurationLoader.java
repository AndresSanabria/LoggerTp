package logger;

import java.io.IOException;

import logger.configurationReaders.Configuration;
import logger.configurationReaders.ConfigurationReader;
import logger.configurationReaders.PropertiesFileReader;
import logger.configurationReaders.XMLFileReader;
import logger.formatters.Formatter;
import logger.formatters.SimpleFormatter;

/**
 * The Class ConfigurationLoader load the Configuration of the Logger from the configuration file.
 */
public class ConfigurationLoader {

	/** The Constant DISTANCE_CALLER_GIVE_FORMAT. */
	private static final Integer DISTANCE_CALLER_GIVE_FORMAT = 4;

	/** The Constant PROPERTIES_FILE_PATH. */
	private static final String PROPERTIES_FILE_PATH = "configFiles/config.properties";

	/** The Constant XML_FILE_PATH. */
	private static final String XML_FILE_PATH = "configFiles/config.xml";

	/** The Constant DEFAULT_CONSOLE. */
	private static final Boolean DEFAULT_CONSOLE = true;

	/** The Constant DEFAULT_LEVEL. */
	private static final String DEFAULT_LEVEL = "OFF";

	/** The Constant DEFAULT_MESSAGE_FORMAT_LEVEL. */
	private static final String DEFAULT_MESSAGE_FORMAT = "%p %n %t %n %m";

	/** The configuration. */
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
	public final Formatter initializeFormatter() {
		Formatter formatter;
		String format = this.getConfiguration().getMessageFormat();
		String separator = this.getConfiguration().getMessageSeparator();
		if (separator != null) {
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
	public final Configuration getConfiguration() {
		return this.configuration;
	}

	/**
	 * Load configuration.
	 *
	 * @param configReader the configuration reader
	 */
	private void loadConfiguration(final ConfigurationReader configReader) {
		try {
			this.configuration = configReader.readConfiguration();
		} catch (IOException e) { }
	}

	/**
	 * Initializes configuration by default values.
	 */
	private void initializeByDefault() {
		this.configuration = new Configuration();
		this.configuration.setLevel(DEFAULT_LEVEL);
		this.configuration.setMessageFormat(DEFAULT_MESSAGE_FORMAT);
		this.configuration.setMessageSeparator(null);
		this.configuration.setLogToFiles(null);
		this.configuration.setLogToConsole(DEFAULT_CONSOLE);
		this.configuration.setCustomOutputs(null);
		this.configuration.setRegExFilter(null);
		this.configuration.setCustomFilter(null);
	}

}
