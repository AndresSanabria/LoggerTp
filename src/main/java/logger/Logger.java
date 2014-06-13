package logger;

/**
 * The Class Logger logs messages in different levels.
 */
public class Logger implements Logging {

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
		ConfigurationLoader configLoader = new ConfigurationLoader();
		this.genericLogger = configLoader.getLogger();
	}

	@Override
	public final String getName() {
		return this.name;
	}

	@Override
	public final void trace(final String logMsg, final Throwable exception) throws Throwable {
		this.genericLogger.log(new Level("TRACE", levelManager.getLevelValue("TRACE")), logMsg, exception);
	}

	@Override
	public final void trace(final String logMsg) {
		try {
			this.genericLogger.log(new Level("TRACE", levelManager.getLevelValue("TRACE")), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	@Override
	public final void debug(final String logMsg, final Throwable exception) throws Throwable {
		this.genericLogger.log(new Level("DEBUG", levelManager.getLevelValue("DEBUG")), logMsg, exception);
	}

	@Override
	public final void debug(final String logMsg) {
		try {
			this.genericLogger.log(new Level("DEBUG", levelManager.getLevelValue("DEBUG")), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	@Override
	public final void info(final String logMsg, final Throwable exception) throws Throwable {
		this.genericLogger.log(new Level("INFO", levelManager.getLevelValue("INFO")), logMsg, exception);
	}

	@Override
	public final void info(final String logMsg) {
		try {
			this.genericLogger.log(new Level("INFO", levelManager.getLevelValue("INFO")), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	@Override
	public final void warn(final String logMsg, final Throwable exception) throws Throwable {
		this.genericLogger.log(new Level("WARN", levelManager.getLevelValue("WARN")), logMsg, exception);
	}

	@Override
	public final void warn(final String logMsg) {
		try {
			this.genericLogger.log(new Level("WARN", levelManager.getLevelValue("WARN")), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	@Override
	public final void error(final String logMsg, final Throwable exception) throws Throwable {
		this.genericLogger.log(new Level("ERROR", levelManager.getLevelValue("ERROR")), logMsg, exception);
	}

	@Override
	public final void error(final String logMsg) {
		try {
			this.genericLogger.log(new Level("ERROR", levelManager.getLevelValue("ERROR")), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	@Override
	public final void fatal(final String logMsg, final Throwable exception) throws Throwable {
		this.genericLogger.log(new Level("FATAL", levelManager.getLevelValue("FATAL")), logMsg, exception);
	}

	@Override
	public final void fatal(final String logMsg) {
		try {
			this.genericLogger.log(new Level("FATAL", levelManager.getLevelValue("FATAL")), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

}
