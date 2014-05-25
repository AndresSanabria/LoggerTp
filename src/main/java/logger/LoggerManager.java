package main.java.logger;

import java.io.IOException;


public class LoggerManager {
	
	Logger logger;
	
	
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
			formatter = new SimpleFormatter(format, separator);
		} else {
			formatter = new SimpleFormatter(format);
		}
		return formatter;
	}

	private void initializeLogger(ConfigurationLoader configLoader, Formatter formatter) throws IOException {
		this.logger = new Logger(formatter, configLoader.getLevel());
		if (configLoader.getLogToConsole()) {
			this.logger.enableConsoleOutput();
		}
		String[] fileOutputs = configLoader.getLogToFiles();
		if (fileOutputs != null) {
			for (String fileOutput: fileOutputs) {
				this.logger.addOutput(new FileOutput(fileOutput));
			}
		}
	}
	
}
