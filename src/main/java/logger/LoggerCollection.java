package main.java.logger;

import java.util.ArrayList;
import java.util.List;

public class LoggerCollection {
	
	private static LoggerCollection loggerCollection;
	private List<Logger> loggers;
	
	private LoggerCollection() {
		loggers = new ArrayList<>();	
	}
	
	public static LoggerCollection getInstance() {
		if (loggerCollection == null) {
			loggerCollection = new LoggerCollection();
		}
		return loggerCollection;
	}
	
	public void addLogger(Logger logger) {
		if (logger == null) throw new NullPointerException();
		loggers.add(logger);
	}
	
	public Logger getLogger(String name) {
		for (Logger logger: loggers) {
			if (logger.getName().equals(name)) {
				return logger;
			}
		}
		return null;
	}
	
	public void clearCollection() {
		loggers = new ArrayList<>();
	}
}
