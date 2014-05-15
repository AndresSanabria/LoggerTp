package main.java.logger;

import java.io.IOException;


public class LoggerManager {

	ConfigurationLoader configLoader;
	Formatter formatter;
	Logger logger;
	
	public LoggerManager() {
		this.configLoader = new ConfigurationLoader();
	}
	
	public void loadConfiguration(String configFilePath) throws IOException {
		this.configLoader.loadConfiguration(configFilePath);
		this.initializeFormatter();
		this.initializeLogger();
	}
	
	public Logger getLogger() {
		return this.logger;
	}
	
	private void initializeFormatter() {
		String format = this.configLoader.getMessageFormat();
		String separator = this.configLoader.getMessageSeparator();
		if (separator != null && !separator.isEmpty()) {
			this.formatter = new SimpleFormatter(format, separator);
		} else {
			this.formatter = new SimpleFormatter(format);
		}
	}

	private void initializeLogger() throws IOException {
		this.logger = new Logger(this.formatter, this.configLoader.getLevel());
		if (this.configLoader.getLogToConsole()) {
			this.logger.addConsoleOutput();
		}
		String[] fileOutputs = this.configLoader.getLogToFiles();
		for (int i = 0; i < (fileOutputs.length - 1); i++) {
			this.logger.addFileOutput(fileOutputs[i]);
		}
	}
	
}
