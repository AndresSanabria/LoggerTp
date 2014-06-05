package logger.configurationReaders;

import java.io.FileInputStream;
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
	private static final Boolean DEFAULT_CONSOLE = false;
	
	/** The Constant DEFAULT_LEVEL. */
	private static final String DEFAULT_LEVEL = "OFF";
	
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
		config.setMessageFormat(this.getPropertyValue(properties, MESSAGE_FORMAT_TAG));
		config.setMessageSeparator(this.getPropertyValue(properties, MESSAGE_SEPARATOR_TAG));
		config.setLogToFiles(this.getPropertyValue(properties, LOG_TO_FILES_TAG).split(OUTPUT_SEPARATOR));
		config.setLogToConsole(this.getLogToConsolePropertyValue(properties));
		config.setCustomOutputs(this.getCustomOutputsPropertyValue(properties));
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
		String value = this.getPropertyValue(properties, LEVEL_TAG);
		if (value.isEmpty()) {
			value = DEFAULT_LEVEL;
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
		String value = this.getPropertyValue(properties, LOG_TO_CONSOLE_TAG);
		if (value.isEmpty()) {
			return DEFAULT_CONSOLE;
		}
		return Boolean.valueOf(value);
	}
	
	/**
	 * Gets the custom outputs property value.
	 *
	 * @param properties the properties parsed
	 * @return the custom outputs property value
	 */
	private List<String[]> getCustomOutputsPropertyValue(Properties properties) {
		List<String[]> customOutputs = new ArrayList<String[]>();
		String[] values = this.getPropertyValue(properties, CUSTOM_OUTPUTS_TAG).split(OUTPUT_SEPARATOR);
		for (String value: values) {
			customOutputs.add(value.split(PARAM_SEPARATOR));
		}
		return customOutputs;
	}

}
