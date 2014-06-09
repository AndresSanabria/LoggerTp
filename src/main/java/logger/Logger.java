package logger;
import java.util.ArrayList;
import java.util.List;

import logger.filters.Filterer;
import logger.formatters.Formatter;
import logger.writables.Writable;
import logger.writables.WriteException;

/**
 * The Class Logger logs messages in the given outputs with the proper format.
 */
public class Logger {

	/** The Constant WRITE_ERROR. */
	private static final String WRITE_ERROR = "An error occured when writing log";

	/** The name. */
	private String name;

	/** The formatter. */
	private Formatter formatter;

	/** The filter. */
	private Filterer filter;

	/** The outputs where to log. */
	private List<Writable> outputs;

	/** The configuration level. */
	private Level level;

	/** The level manager. */
	private LevelManager levelManager;


	/**
	 * Instantiates a new logger.
	 *
	 * @param name the name of the logger
	 * @param level the level of the logger
	 * @param formatter the formatter to format the messages
	 */
	public Logger(final String name, final Level level, final Formatter formatter) {
		super();
		this.name = name;
		this.formatter = formatter;
		this.levelManager = new LevelManager();
		this.level = level;
		this.outputs = new ArrayList<>();
		this.filter = null;
	}


	/**
	 * Adds the output where to Log.
	 *
	 * @param newOutput the new output to be add
	 */
	public final void addOutput(final Writable newOutput) {
		outputs.add(newOutput);
	}

	/**
	 * Sets the filter.
	 *
	 * @param filter the filter to be set
	 */
	public final void setFilter(final Filterer filter) {
		this.filter = filter;
	}

	/**
	 * Log in Trace Level.
	 *
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	public final void trace(final String logMsg, final Throwable exception) throws Throwable {
		log(new Level("TRACE", levelManager.getLevelValue("TRACE")), logMsg, exception);
	}

	/**
	 * Log in Trace Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void trace(final String logMsg) {
		try {
			log(new Level("TRACE", levelManager.getLevelValue("TRACE")), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	/**
	 * Log in Debug Level.
	 *
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	public final void debug(final String logMsg, final Throwable exception) throws Throwable {
		log(new Level("DEBUG", levelManager.getLevelValue("DEBUG")), logMsg, exception);
	}

	/**
	 * Log in Debug Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void debug(final String logMsg) {
		try {
			log(new Level("DEBUG", levelManager.getLevelValue("DEBUG")), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	/**
	 * Log in Info Level.
	 *
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	public final void info(final String logMsg, final Throwable exception) throws Throwable {
		log(new Level("INFO", levelManager.getLevelValue("INFO")), logMsg, exception);
	}

	/**
	 * Log in Info Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void info(final String logMsg) {
		try {
			log(new Level("INFO", levelManager.getLevelValue("INFO")), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	/**
	 * Log in Warn Level.
	 *
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	public final void warn(final String logMsg, final Throwable exception) throws Throwable {
		log(new Level("WARN", levelManager.getLevelValue("WARN")), logMsg, exception);
	}

	/**
	 * Log in Warn Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void warn(final String logMsg) {
		try {
			log(new Level("WARN", levelManager.getLevelValue("WARN")), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	/**
	 * Log in Error Level.
	 *
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	public final void error(final String logMsg, final Throwable exception) throws Throwable {
		log(new Level("ERROR", levelManager.getLevelValue("ERROR")), logMsg, exception);
	}

	/**
	 * Log in Error Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void error(final String logMsg) {
		try {
			log(new Level("ERROR", levelManager.getLevelValue("ERROR")), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	/**
	 * Log in Fatal Level.
	 *
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	public final void fatal(final String logMsg, final Throwable exception) throws Throwable {
		log(new Level("FATAL", levelManager.getLevelValue("FATAL")), logMsg, exception);
	}

	/**
	 * Log in Fatal Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void fatal(final String logMsg) {
		try {
			log(new Level("FATAL", levelManager.getLevelValue("FATAL")), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	/**
	 * Should the Logger log in this Level.
	 *
	 * @param level the level to check if should log
	 * @return true if should log, otherwise false
	 */
	private Boolean shouldLog(final Level level) {
		return this.level.isGreaterThan(level);
	}

	/**
	 * Should the message be filtered.
	 *
	 * @param msg the message to check if it should be filtered
	 * @return true if it should be filtered, otherwise false
	 */
	private Boolean shouldFilter(final String msg) {
		if ((this.filter != null) && (this.filter.filter(msg))) {
			return true;
		} else if (this.filter == null) {
			return true;
		}
		return false;
	}

	/**
	 * Log the message in the given Level.
	 *
	 * @param level the level in which to log
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	private void log(final Level level, final String logMsg, final Throwable exception) throws Throwable {
		if (!shouldLog(level)) {
			return;
		}
		String formatedLog = formatter.giveFormat(level, logMsg);
		if (this.shouldFilter(formatedLog)) {
			for (Writable output: outputs) {
				try {
					output.write(formatedLog);
				} catch (WriteException e) {
					e.printStackTrace();
					handleException(output.getStringId(), exception);
				}
			}
		}
	}

	/**
	 * Handle exception caught.
	 *
	 * @param outputName the name of the output
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	private void handleException(final String outputName, final Throwable exception) throws Throwable {
		handleException(outputName);
		if (exception != null) {
			throw exception;
		}
	}

	/**
	 * Handle exception caught.
	 *
	 * @param outputName the name of the output
	 */
	private void handleException(final String outputName) {
		System.err.println(WRITE_ERROR + " - " + outputName);
	}

	/**
	 * Handle exception caught.
	 *
	 * @return the logger name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Handle exception caught.
	 *
	 * @param name the name of the logger
	 */
	public final void setName(final String name) {
		this.name = name;
	}

}
