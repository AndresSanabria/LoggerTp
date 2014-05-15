package main.java.logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ConfigurationLoader {
	
	private static final String FILE_SEPARATOR = ";";
	
	private String level;
	private String messageFormat;
	private String messageSeparator;
	private String[] logToFiles;
	private Boolean logToConsole;
	
	
	public ConfigurationLoader() {
		this.level = "OFF";
		this.messageFormat = "";
		this.messageSeparator = "";
		this.logToFiles = null;
		this.logToConsole = false;
	}
	
	public void loadConfiguration(String configFilePath) {
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getLevel() {
		return this.level;
	}

	public String getMessageFormat() {
		return this.messageFormat;
	}

	public String getMessageSeparator() {
		return this.messageSeparator;
	}

	public String[] getLogToFiles() {
		return this.logToFiles;
	}

	public Boolean getLogToConsole() {
		return this.logToConsole;
	}
	
	private String getPropertyValue(Properties properties, String key) {
		String value = properties.getProperty(key);
		if (value != null) {
			return value;
		}
		return "";
	}
	
	private String getLevelPropertyValue(Properties properties) {
		String value = this.getPropertyValue(properties, "level");
		if (value.isEmpty()) {
			value = "OFF";
		}
		return value;
	}
	
	private Boolean getLogToConsolePropertyValue(Properties properties) {
		String value = this.getPropertyValue(properties, "logToConsole");
		if (value.isEmpty()) {
			return false;
		}
		return Boolean.valueOf(value);
	}
	
}
