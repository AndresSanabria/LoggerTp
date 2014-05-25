package main.java.logger;

import java.io.IOException;


public class LoggerManager {
	
	Logger logger;
	
	final Integer DISTANCE_CALLER_GIVE_FORMAT = 4;
	
	public LoggerManager() { }
	
	public void loadConfiguration(String configFilePath) throws IOException {
		ConfigurationLoader configLoader = new ConfigurationLoader();
		configLoader.loadConfiguration(configFilePath);
		Formatter formatter = this.initializeFormatter(configLoader);
		this.initializeLogger(configLoader, formatter);
	}
	
	public Logger getLogger() {
		return this.logger;
	}
	
	private Formatter initializeFormatter(ConfigurationLoader configLoader) {
		Formatter formatter;
		String format = configLoader.getMessageFormat();
		String separator = configLoader.getMessageSeparator();
		if (separator != null && !separator.isEmpty()) {
			formatter = new SimpleFormatter(format, DISTANCE_CALLER_GIVE_FORMAT, separator);
		} else {
			formatter = new SimpleFormatter(format, DISTANCE_CALLER_GIVE_FORMAT);
		}
		return formatter;
	}

	private void initializeLogger(ConfigurationLoader configLoader, Formatter formatter) throws IOException {
		this.logger = new Logger(formatter, configLoader.getLevel());
		if (configLoader.getLogToConsole()) {
			this.logger.addConsoleOutput();
		}
		String[] fileOutputs = configLoader.getLogToFiles();
		if (fileOutputs != null) {
			for (int i = 0; i < (fileOutputs.length - 1); i++) {
				this.logger.addFileOutput(fileOutputs[i]);
			}
		}
	}
	
}
