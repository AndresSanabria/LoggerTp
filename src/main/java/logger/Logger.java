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
	 * Instantiates a new logger with its configuration loaded from default files
	 * or by default.
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
	public final void initializeFromConfigFile(final String configFile) {
		ConfigurationLoader configLoader = new ConfigurationLoader(configFile);
		this.genericLogger = configLoader.getLogger();
	}

	@Override
	public final String getName() {
		return this.name;
	}

	@Override
	public final void trace(final String logMsg, final Throwable exception) throws Throwable {
		this.genericLogger.log(levelManager.getLevel("TRACE"), logMsg, exception);
	}

	@Override
	public final void trace(final String logMsg) {
		try {
			this.genericLogger.log(levelManager.getLevel("TRACE"), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	@Override
	public final void debug(final String logMsg, final Throwable exception) throws Throwable {
		this.genericLogger.log(levelManager.getLevel("DEBUG"), logMsg, exception);
	}

	@Override
	public final void debug(final String logMsg) {
		try {
			this.genericLogger.log(levelManager.getLevel("DEBUG"), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	@Override
	public final void info(final String logMsg, final Throwable exception) throws Throwable {
		this.genericLogger.log(levelManager.getLevel("INFO"), logMsg, exception);
	}

	@Override
	public final void info(final String logMsg) {
		try {
			this.genericLogger.log(levelManager.getLevel("INFO"), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	@Override
	public final void warn(final String logMsg, final Throwable exception) throws Throwable {
		this.genericLogger.log(levelManager.getLevel("WARN"), logMsg, exception);
	}

	@Override
	public final void warn(final String logMsg) {
		try {
			this.genericLogger.log(levelManager.getLevel("WARN"), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	@Override
	public final void error(final String logMsg, final Throwable exception) throws Throwable {
		this.genericLogger.log(levelManager.getLevel("ERROR"), logMsg, exception);
	}

	@Override
	public final void error(final String logMsg) {
		try {
			this.genericLogger.log(levelManager.getLevel("ERROR"), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}

	@Override
	public final void fatal(final String logMsg, final Throwable exception) throws Throwable {
		this.genericLogger.log(levelManager.getLevel("FATAL"), logMsg, exception);
	}

	@Override
	public final void fatal(final String logMsg) {
		try {
			this.genericLogger.log(levelManager.getLevel("FATAL"), logMsg, null);
		} catch (Throwable e) {
			// already handled exception in log method
		}
	}
	
	@Override
	public Boolean shouldLog(final Level level) {
		return this.genericLogger.shouldLog(level);
	}

}
