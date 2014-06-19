package logger;

import org.slf4j.helpers.MarkerIgnoringBase;

/**
 * The Class LoggerAdapter adapts the Logger to the SLF4J Logger interface.
 */
public class LoggerAdapter extends MarkerIgnoringBase {

	/** The serial version. */
	private static final long serialVersionUID = 3L;

	/** The logger. */
	private Logger logger;

	/** The Level manager. */
	private LevelManager levelManager = new LevelManager();

	/**
	 * Instantiates a new logger adapter.
	 *
	 * @param logger the logger
	 */
	LoggerAdapter(final Logger logger) {
		this.logger = logger;
	}

	@Override
	public final void debug(final String message) {
		this.logger.debug(message);
	}

	@Override
	public final void debug(final String message, final Object arg1) {
		this.logger.debug(message);
	}

	@Override
	public final void debug(final String message, final Object... arg1) {
		this.logger.debug(message);
	}

	@Override
	public final void debug(final String message, final Throwable exception) {
		try {
			this.logger.debug(message, exception);
		} catch (Throwable e) {
			e.printStackTrace();
			System.err.println("Error while logging:" + exception + " Exception thrown");
		}
	}

	@Override
	public final void debug(final String message, final Object arg1, final Object arg2) {
		this.logger.debug(message);
	}

	@Override
	public final void error(final String message) {
		this.logger.error(message);
	}

	@Override
	public final void error(final String message, final Object arg1) {
		this.logger.error(message);
	}

	@Override
	public final void error(final String message, final Object... arg1) {
		this.logger.error(message);
	}

	@Override
	public final void error(final String message, final Throwable exception) {
		try {
			this.logger.error(message, exception);
		} catch (Throwable e) {
			e.printStackTrace();
			System.err.println("Error while logging:" + exception + " Exception thrown");
		}
	}

	@Override
	public final void error(final String message, final Object arg1, final Object arg2) {
		this.logger.error(message);
	}

	@Override
	public final void info(final String message) {
		this.logger.info(message);
	}

	@Override
	public final void info(final String message, final Object arg1) {
		this.logger.info(message);
	}

	@Override
	public final void info(final String message, final Object... arg1) {
		this.logger.info(message);
	}

	@Override
	public final void info(final String message, final Throwable exception) {
		try {
			this.logger.info(message, exception);
		} catch (Throwable e) {
			e.printStackTrace();
			System.err.println("Error while logging:" + exception + " Exception thrown");
		}
	}

	@Override
	public final void info(final String message, final Object arg1, final Object arg2) {
		this.logger.info(message);
	}

	@Override
	public final boolean isDebugEnabled() {
		return this.logger.shouldLog(levelManager.getLevel("DEBUG"));
	}

	@Override
	public final boolean isErrorEnabled() {
		return this.logger.shouldLog(levelManager.getLevel("ERROR"));
	}

	@Override
	public final boolean isInfoEnabled() {
		return this.logger.shouldLog(levelManager.getLevel("INFO"));
	}

	@Override
	public final boolean isTraceEnabled() {
		return this.logger.shouldLog(levelManager.getLevel("TRACE"));
	}

	@Override
	public final boolean isWarnEnabled() {
		return this.logger.shouldLog(levelManager.getLevel("WARN"));
	}

	@Override
	public final void trace(final String message) {
		this.logger.trace(message);
	}

	@Override
	public final void trace(final String message, final Object arg1) {
		this.logger.trace(message);
	}

	@Override
	public final void trace(final String message, final Object... arg1) {
		this.logger.trace(message);
	}

	@Override
	public final void trace(final String message, final Throwable exception) {
		try {
			this.logger.trace(message, exception);
		} catch (Throwable e) {
			e.printStackTrace();
			System.err.println("Error while logging:" + exception + " Exception thrown");
		}
	}

	@Override
	public final void trace(final String message, final Object arg1, final Object arg2) {
		this.logger.trace(message);
	}

	@Override
	public final void warn(final String message) {
		this.logger.warn(message);
	}

	@Override
	public final void warn(final String message, final Object arg1) {
		this.logger.warn(message);
	}

	@Override
	public final void warn(final String message, final Object... arg1) {
		this.logger.warn(message);
	}

	@Override
	public final void warn(final String message, final Throwable exception) {
		try {
			this.logger.warn(message, exception);
		} catch (Throwable e) {
			e.printStackTrace();
			System.err.println("Error while logging:" + exception + " Exception thrown");
		}
	}

	@Override
	public final void warn(final String message, final Object arg1, final Object arg2) {
		this.logger.warn(message);
	}

}
