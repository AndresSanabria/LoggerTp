package logger;

import static org.junit.Assert.*;
import logger.Logger;
import logger.LoggerCollection;

import org.junit.Before;
import org.junit.Test;

/**
 * The Class TestLoggerCollection tests the LoggerCollection.
 */
public class TestLoggerCollection {

	/** The Constant ONE_LOGGER. */
	private static final String ONE_LOGGER = "one logger";

	/** The Constant OTHER_LOGGER. */
	private static final String OTHER_LOGGER = "other logger";

	/** The instance of one LoggerManager. */
	private LoggerManager loggerManager = new LoggerManager(ONE_LOGGER);

	/** The instance of another LoggerManager. */
	private LoggerManager otherLoggerManager = new LoggerManager(OTHER_LOGGER);

	/** The instance of one Logger. */
	private Logger oneLogger = loggerManager.getLogger();

	/** The instance of another Logger. */
	private Logger otherLogger = otherLoggerManager.getLogger();


	@Before
	public final void cleanUpCollection() {
	    LoggerCollection.getInstance().clearCollection();
	}

	@Test
	public final void oneLoggerCollection() {
		LoggerCollection.getInstance().addLogger(oneLogger);
		assertEquals(oneLogger, LoggerCollection.getInstance().getLogger(ONE_LOGGER));
	}

	@Test
	public final void twoLoggerCollection() {
		LoggerCollection.getInstance().addLogger(oneLogger);
		LoggerCollection.getInstance().addLogger(otherLogger);
		assertEquals(oneLogger, LoggerCollection.getInstance().getLogger(ONE_LOGGER));
		assertEquals(otherLogger, LoggerCollection.getInstance().getLogger(OTHER_LOGGER));
	}

	@Test(expected = NullPointerException.class)
	public final void addNullLogger() {
		LoggerCollection.getInstance().addLogger(null);
	}

	@Test
	public final void getInvalidLogger() {
		assertEquals(LoggerCollection.getInstance().getLogger("XYZ"), null);
	}

}
