package logger;
import java.util.ArrayList;
import java.util.List;

import logger.filters.Filterer;
import logger.formatters.Formatter;
import logger.outputs.Output;
import logger.outputs.OutputException;

/**
 * The Class GenericLogger logs messages in the given outputs with the proper format.
 */
public class GenericLogger {

	/** The Constant WRITE_ERROR. */
	private static final String WRITE_ERROR = "An error occured when writing log";

	/** The formatter. */
	private Formatter formatter;

	/** The filter. */
	private Filterer filter;

	/** The outputs where to log. */
	private List<Output> outputs;

	/** The configuration level. */
	private Level level;


	/**
	 * Instantiates a new generic logger.
	 *
	 * @param level the level of the logger
	 * @param formatter the formatter to format the messages
	 */
	public GenericLogger(final Level level, final Formatter formatter) {
		super();
		this.formatter = formatter;
		this.level = level;
		this.outputs = new ArrayList<>();
		this.filter = null;
	}

	/**
	 * Adds the output where to Log.
	 *
	 * @param newOutput the new output to be add
	 */
	public final void addOutput(final Output newOutput) {
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
	 * Log the message in the given Level.
	 *
	 * @param level the level in which to log
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	public final void log(final Level level, final String logMsg, final Throwable exception) throws Throwable {
		if (!shouldLog(level)) {
			return;
		}
		String formatedLog = formatter.giveFormat(level, logMsg);
		if (this.shouldFilter(formatedLog)) {
			for (Output output: outputs) {
				try {
					output.write(formatedLog);
				} catch (OutputException e) {
					e.printStackTrace();
					handleException(output.getStringId(), exception);
				}
			}
		}
	}

	/**
	 * Should the logger log in this Level.
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

}
