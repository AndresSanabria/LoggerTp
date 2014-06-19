package logger;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

/**
 * The binding of LoggerFactory class with an actual instance of ILoggerFactory.
 */
public class StaticLoggerBinder implements LoggerFactoryBinder {

	/**
	 * The unique instance of this class.
	 */
	private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

	/**
	 * Return the singleton of this class.
	 *
	 * @return the StaticLoggerBinder singleton
	 */
	public static final StaticLoggerBinder getSingleton() {
		return SINGLETON;
	}

	/**
	 * Version tag used to check compatibility. The value of this field is
	 * modified with each release.
	 */

	//to avoid constant folding by the compiler, this field must *not* be final
	public static String REQUESTED_API_VERSION = "1.7.7";

	/**
	 * Binding specific code.
	 */
	private static final String LOGGER_FACTORY_CLASS_STR = logger.LoggerSLF4JFactory.class
			.getName();

	/**
	 * The ILoggerFactory instance returned by the {@link #getLoggerFactory}
	 * method should always be the same object.
	 */
	private final ILoggerFactory loggerFactory;

	private StaticLoggerBinder() {
		// Binding specific code:
		loggerFactory = new logger.LoggerSLF4JFactory();
	}

	public ILoggerFactory getLoggerFactory() {
		return loggerFactory;
	}

	public String getLoggerFactoryClassStr() {
		return LOGGER_FACTORY_CLASS_STR;
	}

}
