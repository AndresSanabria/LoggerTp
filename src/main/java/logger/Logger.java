package logger;

/**
 * The Class Logger logs messages in different levels.
 */
public class Logger {

	/** The name. */
	private String name;

	/** The level manager. */
	private LevelManager levelManager;

	/** The generic logger. */
	private GenericLogger genericLogger;


	/**
	 * Instantiates a new logger.
	 *
	 * @param name the name of the logger
	 */
	public Logger(final String name) {
		super();
		this.name = name;
		this.levelManager = new LevelManager();
		LoggerConfigurator loggerManager = new LoggerConfigurator();
		this.genericLogger = loggerManager.getLogger();
	}

	/**
	 * Gets the name of the logger.
	 *
	 * @return the logger name
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * Log in Trace Level.
	 *
	 * @param logMsg the message to log
	 * @param exception the exception to be thrown
	 * @throws Throwable when there is an exception
	 */
	public final void trace(final String logMsg, final Throwable exception) throws Throwable {
		this.genericLogger.log(new Level("TRACE", levelManager.getLevelValue("TRACE")), logMsg, exception);
	}

	/**
	 * Log in Trace Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void trace(final String logMsg) {
		try {
			this.genericLogger.log(new Level("TRACE", levelManager.getLevelValue("TRACE")), logMsg, null);
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
		this.genericLogger.log(new Level("DEBUG", levelManager.getLevelValue("DEBUG")), logMsg, exception);
	}

	/**
	 * Log in Debug Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void debug(final String logMsg) {
		try {
			this.genericLogger.log(new Level("DEBUG", levelManager.getLevelValue("DEBUG")), logMsg, null);
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
		this.genericLogger.log(new Level("INFO", levelManager.getLevelValue("INFO")), logMsg, exception);
	}

	/**
	 * Log in Info Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void info(final String logMsg) {
		try {
			this.genericLogger.log(new Level("INFO", levelManager.getLevelValue("INFO")), logMsg, null);
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
		this.genericLogger.log(new Level("WARN", levelManager.getLevelValue("WARN")), logMsg, exception);
	}

	/**
	 * Log in Warn Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void warn(final String logMsg) {
		try {
			this.genericLogger.log(new Level("WARN", levelManager.getLevelValue("WARN")), logMsg, null);
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
		this.genericLogger.log(new Level("ERROR", levelManager.getLevelValue("ERROR")), logMsg, exception);
	}

	/**
	 * Log in Error Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void error(final String logMsg) {
		try {
			this.genericLogger.log(new Level("ERROR", levelManager.getLevelValue("ERROR")), logMsg, null);
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
		this.genericLogger.log(new Level("FATAL", levelManager.getLevelValue("FATAL")), logMsg, exception);
	}

	/**
	 * Log in Fatal Level.
	 *
	 * @param logMsg the message to log
	 */
	public final void fatal(final String logMsg) {
		try {
			this.genericLogger.log(new Level("FATAL", levelManager.getLevelValue("FATAL")), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

}
