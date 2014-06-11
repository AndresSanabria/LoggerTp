package logger;

/**
 * The Interface Logging thought to log messages in different levels.
 */
public interface Logging {

	/**
	 * Gets the name of the logger.
	 *
	 * @return the logger name
	 */
	String getName();

	/**
	 * Log in Trace Level.
	 *
	 * @param logMsg the message to log
	 */
	void trace(final String logMsg);

	/**
	 * Log in Trace Level.
	 *
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	void trace(final String logMsg, final Throwable exception) throws Throwable;

	/**
	 * Log in Debug Level.
	 *
	 * @param logMsg the message to log
	 */
	void debug(final String logMsg);

	/**
	 * Log in Debug Level.
	 *
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	void debug(final String logMsg, final Throwable exception) throws Throwable;

	/**
	 * Log in Info Level.
	 *
	 * @param logMsg the message to log
	 */
	void info(final String logMsg);

	/**
	 * Log in Info Level.
	 *
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	void info(final String logMsg, final Throwable exception) throws Throwable;

	/**
	 * Log in Warn Level.
	 *
	 * @param logMsg the message to log
	 */
	void warn(final String logMsg);

	/**
	 * Log in Warn Level.
	 *
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	void warn(final String logMsg, final Throwable exception) throws Throwable;

	/**
	 * Log in Error Level.
	 *
	 * @param logMsg the message to log
	 */
	void error(final String logMsg);

	/**
	 * Log in Error Level.
	 *
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	void error(final String logMsg, final Throwable exception) throws Throwable;

	/**
	 * Log in Fatal Level.
	 *
	 * @param logMsg the message to log
	 */
	void fatal(final String logMsg);

	/**
	 * Log in Fatal Level.
	 *
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	void fatal(final String logMsg, final Throwable exception) throws Throwable;

}
