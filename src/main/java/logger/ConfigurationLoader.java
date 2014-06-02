package main.java.logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * The Class ConfigurationLoader load the Configuration of the Logger from the configuration file
 */
public class ConfigurationLoader {
	
	/** The Constant FILE_SEPARATOR. */
	private static final String FILE_SEPARATOR = ";";
	
	/** The Constant DISTANCE_CALLER_GIVE_FORMAT. */
	private static final Integer DISTANCE_CALLER_GIVE_FORMAT = 4;
	
	/** The level of the Logger. */
	private String level;
	
	/** The format in which the log will be written. */
	private String messageFormat;
	
	/** The message separator. */
	private String messageSeparator;
	
	/** The files where to log. */
	private String[] logToFiles;
	
	/** Should log to Console?. */
	private Boolean logToConsole;
	
	
	/**
	 * Instantiates a new configuration loader.
	 *
	 * @param configFilePath the configuration file path
	 */
	public ConfigurationLoader(String configFilePath) {
		this.loadConfiguration(configFilePath);
	}
	
	/**
	 * Initialize formatter.
	 *
	 * @return the formatter
	 */
	public Formatter initializeFormatter() {
		Formatter formatter;
		String format = this.getMessageFormat();
		String separator = this.getMessageSeparator();
		if (separator != null && !separator.isEmpty()) {
			formatter = new SimpleFormatter(format, DISTANCE_CALLER_GIVE_FORMAT, separator);
		} else {
			formatter = new SimpleFormatter(format, DISTANCE_CALLER_GIVE_FORMAT);
		}
		return formatter;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public String getLevel() {
		return this.level;
	}

	/**
	 * Gets the message format.
	 *
	 * @return the message format
	 */
	public String getMessageFormat() {
		return this.messageFormat;
	}

	/**
	 * Gets the message separator.
	 *
	 * @return the message separator
	 */
	public String getMessageSeparator() {
		return this.messageSeparator;
	}

	/**
	 * Gets files where to log.
	 *
	 * @return the files to log
	 */
	public String[] getLogToFiles() {
		return this.logToFiles;
	}

	/**
	 * Gets if log to console.
	 *
	 * @return if should log to console
	 */
	public Boolean getLogToConsole() {
		return this.logToConsole;
	}
	
	/**
	 * Load configuration.
	 *
	 * @param configFilePath the configuration file path
	 */
	private void loadConfiguration(String configFilePath) {
		Properties properties = new Properties();
		FileInputStream configFile;
		try {
			configFile = new FileInputStream(configFilePath);
			properties.load(configFile);
			this.level = this.getLevelPropertyValue(properties);
			this.messageFormat = this.getPropertyValue(properties, "messageFormat");
			this.messageSeparator = this.getPropertyValue(properties, "messageSeparator");
			this.logToFiles = this.getPropertyValue(properties, "logToFiles").split(FILE_SEPARATOR);
			this.logToConsole = this.getLogToConsolePropertyValue(properties);
		} catch (FileNotFoundException e) {
			System.err.println("File: "+ configFilePath + "was not found: "+ e.getMessage());
			System.err.println("Check your Configuration file");
		} catch (IOException e) {
			System.err.println("There was an IOException when Loading Configuration: "+ e.getMessage());
			System.err.println("Check your Configuration file");
		}
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
