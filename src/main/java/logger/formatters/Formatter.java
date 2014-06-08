package logger.formatters;

import logger.Level;

/**
 * The Interface Formatter thought to give format to messages.
 */
public interface Formatter {

	/**
	 * Give format to the message received.
	 *
	 * @param level the level of Log
	 * @param logMsg the log message
	 * @return the formatted message
	 */
	String giveFormat(Level level, String logMsg);

}
