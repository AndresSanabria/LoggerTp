package logger.configurationReaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The Class PropertiesFileReader reads the configuration from a .properties file.
 */
public class PropertiesFileReader implements ConfigurationReader {

	/** The Constant OUTPUT_SEPARATOR. */
	private static final String OUTPUT_SEPARATOR = ";";

	/** The Constant PARAM_SEPARATOR. */
	private static final String PARAM_SEPARATOR = ",";

	/** The Constant DEFAULT_CONSOLE. */
	private static final String DEFAULT_CONSOLE = "false";

	/** The Constant DEFAULT_LEVEL. */
	private static final String DEFAULT_LEVEL = "OFF";

	/** The Constant DEFAULT_MESSAGE_FORMAT_LEVEL. */
	private static final String DEFAULT_MESSAGE_FORMAT = "%p %n %t %n %m";

	/** The Constant LEVEL_TAG. */
	private static final String LEVEL_TAG = "level";

	/** The Constant MESSAGE_FORMAT_TAG. */
	private static final String MESSAGE_FORMAT_TAG = "messageFormat";

	/** The Constant MESSAGE_SEPARATOR_TAG. */
	private static final String MESSAGE_SEPARATOR_TAG = "messageSeparator";

	/** The Constant CUSTOM_OUTPUTS_TAG. */
	private static final String CUSTOM_OUTPUTS_TAG = "customOutputs";

	/** The Constant LOG_TO_FILES_TAG. */
	private static final String LOG_TO_FILES_TAG = "logToFiles";

	/** The Constant LOG_TO_CONSOLE_TAG. */
	private static final String LOG_TO_CONSOLE_TAG = "logToConsole";

	/** The Constant REGEX_FILTER_TAG. */
	private static final String REGEX_FILTER_TAG = "regExFilter";

	/** The Constant CUSTOM_FILTER_TAG. */
	private static final String CUSTOM_FILTER_TAG = "customFilter";

	/** The file path. */
	private String filePath;


	/**
	 * Instantiates a new PropertiesFileReader.
	 *
	 * @param filePath the path to the file to be read
	 */
	public PropertiesFileReader(final String filePath) {
		this.filePath = filePath;
	}

	@Override
	public final Configuration readConfiguration() throws ReaderException {
		try {
			Configuration config = new Configuration();
			Properties properties = new Properties();
			FileInputStream configFile;
			configFile = new FileInputStream(this.filePath);
			properties.load(configFile);
			config.setLevel(this.getPropertyValue(properties, LEVEL_TAG, DEFAULT_LEVEL));
			config.setMessageFormat(this.getPropertyValue(properties, MESSAGE_FORMAT_TAG, DEFAULT_MESSAGE_FORMAT));
			config.setMessageSeparator(this.getPropertyValue(properties, MESSAGE_SEPARATOR_TAG, null));
			config.setLogToFiles(this.getLogToFilesPropertyValue(properties));
			config.setLogToConsole(Boolean.valueOf(this.getPropertyValue(properties, LOG_TO_CONSOLE_TAG, DEFAULT_CONSOLE)));
			config.setCustomOutputs(this.getCustomOutputsPropertyValue(properties));
			config.setRegExFilter(this.getPropertyValue(properties, REGEX_FILTER_TAG, null));
			config.setCustomFilter(this.getCustomFilterPropertyValue(properties));
			return config;
		} catch (FileNotFoundException e1) {
			throw new ReaderException();
		} catch (IOException e2) {
			throw new ReaderException();
		}
	}

	/**
	 * Gets the property value.
	 *
	 * @param properties the properties parsed
	 * @param propertyTag the tag from which to get the value
	 * @param defaultValue the default value in case the property does not exist
	 * @return the property value
	 */
	private String getPropertyValue(final Properties properties, final String propertyTag, final String defaultValue) {
		String value = properties.getProperty(propertyTag);
		if (value == null) {
			value = defaultValue;
		} else if (value.isEmpty()) {
			value = defaultValue;
		}
		return value;
	}

	/**
	 * Gets the log to files property value.
	 *
	 * @param properties the properties parsed
	 * @return the log to files property value
	 */
	private String[] getLogToFilesPropertyValue(final Properties properties) {
		String values = this.getPropertyValue(properties, LOG_TO_FILES_TAG, null);
		if (values == null) {
			return null;
		}
		return values.split(OUTPUT_SEPARATOR);
	}

	/**
	 * Gets the custom outputs property value.
	 *
	 * @param properties the properties parsed
	 * @return the custom outputs property value
	 */
	private List<String[]> getCustomOutputsPropertyValue(final Properties properties) {
		List<String[]> customOutputs = new ArrayList<>();
		String values = this.getPropertyValue(properties, CUSTOM_OUTPUTS_TAG, null);
		if (values == null) {
			return null;
		}
		String [] outputs = values.split(OUTPUT_SEPARATOR);
		for (String value: outputs) {
			customOutputs.add(value.split(PARAM_SEPARATOR));
		}
		return customOutputs;
	}

	/**
	 * Gets the custom filter property value.
	 *
	 * @param properties the properties parsed
	 * @return the custom filter property value
	 */
	private String[] getCustomFilterPropertyValue(final Properties properties) {
		String values = this.getPropertyValue(properties, CUSTOM_FILTER_TAG, null);
		if (values == null) {
			return null;
		}
		return values.split(PARAM_SEPARATOR);
	}

}
