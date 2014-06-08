package logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class LoggerCollection stores all the instances of the Logger.
 */
public final class LoggerCollection {

	/** The singleton instance. */
	private static LoggerCollection loggerCollection;

	/** The list of loggers. */
	private List<Logger> loggers;

	/**
	 * Instantiates a new LoggerCollection.
	 *
	 */
	private LoggerCollection() {
		loggers = new ArrayList<>();
	}

	/**
	 * Gets the instance of LoggerCollection.
	 *
	 * @return the instance of LoggerCollection
	 */
	public static LoggerCollection getInstance() {
		if (loggerCollection == null) {
			loggerCollection = new LoggerCollection();
		}
		return loggerCollection;
	}

	/**
	 * Adds a new logger to the list.
	 *
	 * @param logger the logger to add
	 */
	public void addLogger(final Logger logger) {
		if (logger == null) {
			throw new NullPointerException();
		}
		loggers.add(logger);
	}

	/**
	 * Gets the logger by name.
	 *
	 * @param name the name of the logger to get
	 * @return the logger
	 */
	public Logger getLogger(final String name) {
		for (Logger logger: loggers) {
			if (logger.getName().equals(name)) {
				return logger;
			}
		}
		return null;
	}
	/**
	 * Clears the logger collection.
	 *
	 */
	public void clearCollection() {
		loggers = new ArrayList<>();
	}
}
