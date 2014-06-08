package logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logger.customFactory.CustomFilterException;
import logger.customFactory.CustomFactory;
import logger.filters.Filterer;
import logger.filters.RegexFilter;
import logger.formatters.Formatter;
import logger.writables.ConsoleOutput;
import logger.customFactory.CustomOutputException;
import logger.writables.FileOutput;
import logger.writables.Writable;
import logger.writables.WriteException;

/**
 * The Class Logger logs messages in the given outputs with the proper format.
 */
public class Logger {

	/** The name. */
	private String name;

	/** The formatter. */
	private Formatter formatter;

	/** The configuration loader. */
	private ConfigurationLoader configLoader;

	/** The filter. */
	private Filterer filter;

	/** The outputs where to log. */
	private List<Writable> outputs;

	/** Is the console active? */
	private boolean consoleActive;

	/** The configuration level. */
	private Level configLevel;

	/**
	 * The Enum levelValues.
	 */
	private enum levelValues { OFF, FATAL, ERROR, WARN, INFO, DEBUG, TRACE }

	/** The Constant WRITE_ERROR. */
	private static final String WRITE_ERROR = "An error occured when writing log";


	/**
	 * Instantiates a new logger.
	 *
	 * @param name the name of the logger
	 */
	public Logger(final String name) {
		super();
		this.name = name;
		this.configLoader = new ConfigurationLoader();
		try {
			this.initializeOutputs();
			this.initializeFilter();
		} catch (IOException e) {
			handleException("There was an IOException when Intializing outputs: " + e.getMessage() + "\n Check your Configuration file");
		} catch (CustomOutputException e) {
			handleException("There was an CustomOutputException when Intializing outputs: " + e.getMessage() + "\n Check your Configuration file");
		} catch (CustomFilterException e) {
			handleException("There was an CustomFilterException when Intializing filter: " + e.getMessage() + "\n Check your Configuration file");
		}
		this.formatter = this.configLoader.initializeFormatter();
		this.consoleActive = false;
		String levelName = this.configLoader.getConfiguration().getLevel();
		this.configLevel = new Level(levelName, levelValues.valueOf(levelName).ordinal());
	}

	/**
	 * Initialize outputs.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws CustomOutputException when an error occurs in the creation of a CustomOutput
	 */
	private void initializeOutputs() throws IOException, CustomOutputException {
		this.outputs = new ArrayList<>();
		if (this.configLoader.getConfiguration().getLogToConsole()) {
			this.enableConsoleOutput();
		}
		String[] fileOutputs = configLoader.getConfiguration().getLogToFiles();
		if (fileOutputs != null) {
			for (String fileOutput: fileOutputs) {
				if (!fileOutput.isEmpty()) {
					this.addOutput(new FileOutput(fileOutput));
				}
			}
		}
		List<String[]> customOutputs = this.configLoader.getConfiguration().getCustomOutputs();
		if (customOutputs != null) {
			CustomFactory outputFactory = new CustomFactory();
			for (String[] customOutput: customOutputs) {
				this.addOutput(outputFactory.createCustomOutput(customOutput[0], java.util.Arrays.copyOfRange(customOutput, 1, customOutput.length)));
			}
		}
	}

	/**
	 * Enable console output.
	 */
	private void enableConsoleOutput() {
		if (!consoleActive) {
			outputs.add(new ConsoleOutput());
			consoleActive = true;
		}
	}

	/**
	 * Initialize filter.
	 *
	 * @throws CustomFilterException when an error occurs in the creation of a CustomFilter
	 */
	private void initializeFilter() throws CustomFilterException {
		String regExFilter = this.configLoader.getConfiguration().getRegExFilter();
		if (regExFilter == null) {
			String[] customFilter = this.configLoader.getConfiguration().getCustomFilter();
			if (customFilter == null) {
				this.filter = null;
			} else {
				CustomFactory filterFactory = new CustomFactory();
				this.filter = filterFactory.createCustomFilter(customFilter[0], java.util.Arrays.copyOfRange(customFilter, 1, customFilter.length));
			}
		} else {
			this.filter = new RegexFilter(regExFilter);
		}
	}

	/**
	 * Adds the output where to Log.
	 *
	 * @param newOutput the new output to be add
	 */
	private void addOutput(final Writable newOutput) {
		outputs.add(newOutput);
	}

	/**
	 * Log in Trace Level.
	 *
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	public final void trace(final String logMsg, final Throwable exception) throws Throwable {
		log(new Level(levelValues.TRACE.name(), levelValues.TRACE.ordinal()), logMsg, exception);
	}

	/**
	 * Log in Trace Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void trace(final String logMsg) {
		try {
			log(new Level(levelValues.TRACE.name(), levelValues.TRACE.ordinal()), logMsg, null);
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
		log(new Level(levelValues.DEBUG.name(), levelValues.DEBUG.ordinal()), logMsg, exception);
	}

	/**
	 * Log in Debug Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void debug(final String logMsg) {
		try {
			log(new Level(levelValues.DEBUG.name(), levelValues.DEBUG.ordinal()), logMsg, null);
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
		log(new Level(levelValues.INFO.name(), levelValues.INFO.ordinal()), logMsg, exception);
	}

	/**
	 * Log in Info Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void info(final String logMsg) {
		try {
			log(new Level(levelValues.INFO.name(), levelValues.INFO.ordinal()), logMsg, null);
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
		log(new Level(levelValues.WARN.name(), levelValues.WARN.ordinal()), logMsg, exception);
	}

	/**
	 * Log in Warn Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void warn(final String logMsg) {
		try {
			log(new Level(levelValues.WARN.name(), levelValues.WARN.ordinal()), logMsg, null);
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
		log(new Level(levelValues.ERROR.name(), levelValues.ERROR.ordinal()), logMsg, exception);
	}

	/**
	 * Log in Error Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void error(final String logMsg) {
		try {
			log(new Level(levelValues.ERROR.name(), levelValues.ERROR.ordinal()), logMsg, null);
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
		log(new Level(levelValues.FATAL.name(), levelValues.FATAL.ordinal()), logMsg, exception);
	}

	/**
	 * Log in Fatal Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void fatal(final String logMsg) {
		try {
			log(new Level(levelValues.FATAL.name(), levelValues.FATAL.ordinal()), logMsg, null);
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
		return this.configLevel.isGreaterThan(level);
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
