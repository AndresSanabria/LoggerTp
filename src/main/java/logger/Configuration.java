package main.java.logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Configuration {
	
	private static final String FILE_SEPARATOR = ";";
	
	private String level;
	private String messageFormat;
	private String messageSeparator;
	private String[] logToFiles;
	private Boolean logToConsole;
	
	
	public Configuration() {
		this.level = "OFF";
		this.messageFormat = "";
		this.messageSeparator = "";
		this.logToConsole = false;
	}
	
	public void loadConfiguration(String configFilePath) {
		Properties properties = new Properties();
		FileInputStream configFile;
		try {
			configFile = new FileInputStream(configFilePath);
			properties.load(configFile);
			this.level = properties.getProperty("level");
			this.messageFormat = properties.getProperty("messageFormat");
			this.messageSeparator = properties.getProperty("messageSeparator");
			this.logToFiles = properties.getProperty("logToFiles").split(FILE_SEPARATOR);
			this.logToConsole = Boolean.valueOf(properties.getProperty("logToConsole"));
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
	
}
