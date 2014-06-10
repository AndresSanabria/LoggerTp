package logger;

import java.io.IOException;
import java.util.List;

import logger.configurationReaders.Configuration;
import logger.configurationReaders.ConfigurationReader;
import logger.configurationReaders.PropertiesFileReader;
import logger.configurationReaders.XMLFileReader;
import logger.customFactory.CustomFactory;
import logger.customFactory.CustomFilterException;
import logger.customFactory.CustomOutputException;
import logger.filters.RegexFilter;
import logger.formatters.Formatter;
import logger.formatters.SimpleFormatter;
import logger.outputs.ConsoleOutput;
import logger.outputs.FileOutput;

/**
 * The Class ConfigurationLoader loads the configuration of the logger from the configuration file.
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
	private static final String DEFAULT_MESSAGE_FORMAT = "%p %n %m";

	/** The configuration. */
	private Configuration configuration;

	/** The logger. */
	private GenericLogger logger;


	/**
	 * Instantiates a new configuration loader.
	 */
	public ConfigurationLoader() {
		this.loadConfiguration();
		this.initializeLogger();
	}

	/**
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public final GenericLogger getLogger() {
		return this.logger;
	}

	/**
	 * Loads the configuration from .properties or .xml files or by default.
	 */
	private void loadConfiguration() {
		this.loadConfigurationFromFile(new PropertiesFileReader(PROPERTIES_FILE_PATH));
		if (this.configuration == null) {
			this.loadConfigurationFromFile(new XMLFileReader(XML_FILE_PATH));
			if (this.configuration == null) {
				this.initializeByDefault();
			}
		}
	}

	/**
	 * Load configuration.
	 *
	 * @param configReader the configuration reader
	 */
	private void loadConfigurationFromFile(final ConfigurationReader configReader) {
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

	/**
	 * Initializes the logger.
	 */
	private void initializeLogger() {
		Formatter formatter = this.initializeFormatter();
		String levelName = this.configuration.getLevel();
		LevelManager levelManager = new LevelManager();
		Level level = new Level(levelName, levelManager.getLevelValue(levelName));
		this.logger = new GenericLogger(level, formatter);
		try {
			this.initializeOutputs();
			this.initializeFilter();
		} catch (IOException e) {
			handleException("There was an IOException when Intializing outputs: " + e.getMessage() + "\n Check your Configuration file");
		} catch (CustomOutputException e) {
			handleException("There was an CustomOutputException when Intializing outputs: " + e.getMessage() + "\n Check your Configuration file");
		} catch (CustomFilterException e) {
			handleException("There was an CustomFilterException when Intializing filter: " + e.getMessage() + "\n Check your Configuration file");
		}
	}

	/**
	 * Initializes the formatter.
	 *
	 * @return the formatter initialized
	 */
	private Formatter initializeFormatter() {
		Formatter formatter;
		String format = this.configuration.getMessageFormat();
		String separator = this.configuration.getMessageSeparator();
		if (separator != null) {
			formatter = new SimpleFormatter(format, DISTANCE_CALLER_GIVE_FORMAT, separator);
		} else {
			formatter = new SimpleFormatter(format, DISTANCE_CALLER_GIVE_FORMAT);
		}
		return formatter;
	}

	/**
	 * Initializes outputs.
	 *
	 * @throws IOException Signals that an I/O exception has occurred
	 * @throws CustomOutputException when an error occurs in the creation of a CustomOutput
	 */
	private void initializeOutputs() throws IOException, CustomOutputException {
		if (this.configuration.getLogToConsole()) {
			this.logger.addOutput(new ConsoleOutput());
		}
		String[] fileOutputs = this.configuration.getLogToFiles();
		if (fileOutputs != null) {
			for (String fileOutput: fileOutputs) {
				if (!fileOutput.isEmpty()) {
					this.logger.addOutput(new FileOutput(fileOutput));
				}
			}
		}
		List<String[]> customOutputs = this.configuration.getCustomOutputs();
		if (customOutputs != null) {
			CustomFactory outputFactory = new CustomFactory();
			for (String[] customOutput: customOutputs) {
				this.logger.addOutput(outputFactory.createCustomOutput(customOutput[0], java.util.Arrays.copyOfRange(customOutput, 1, customOutput.length)));
			}
		}
	}

	/**
	 * Initializes filter.
	 *
	 * @throws CustomFilterException when an error occurs in the creation of a CustomFilter
	 */
	private void initializeFilter() throws CustomFilterException {
		String regExFilter = this.configuration.getRegExFilter();
		if (regExFilter == null) {
			String[] customFilter = this.configuration.getCustomFilter();
			if (customFilter == null) {
				this.logger.setFilter(null);
			} else {
				CustomFactory filterFactory = new CustomFactory();
				this.logger.setFilter(filterFactory.createCustomFilter(customFilter[0], java.util.Arrays.copyOfRange(customFilter, 1, customFilter.length)));
			}
		} else {
			this.logger.setFilter(new RegexFilter(regExFilter));
		}
	}

	/**
	 * Handle exception caught.
	 *
	 * @param message the message to be shown
	 */
	private void handleException(final String message) {
		System.err.println(message);
	}

}
