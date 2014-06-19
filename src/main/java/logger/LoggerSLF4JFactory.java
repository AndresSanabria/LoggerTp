package logger;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * The Class LoggerFactory generates the SLF4J Logger.
 */
public class LoggerSLF4JFactory implements ILoggerFactory {

	/** The map with loggers. */
	private Map<String, Logger> loggerMap;


	/**
	 * Instantiates a new logger factory.
	 */
	public LoggerSLF4JFactory() {
		loggerMap = new HashMap<String, Logger>();
	}

	@Override
	public final Logger getLogger(final String name) {
		Logger slf4jLogger = null;
		// Protect against concurrent access of loggerMap
		synchronized (this) {
			slf4jLogger = loggerMap.get(name);
			if (slf4jLogger == null) {
				logger.Logger logger = new logger.Logger(name);
				slf4jLogger = new LoggerAdapter(logger);
				loggerMap.put(name, slf4jLogger);
			}
		}
		return slf4jLogger;
	}

}
