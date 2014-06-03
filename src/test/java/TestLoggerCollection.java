package test.java;

import static org.junit.Assert.*;


import main.java.logger.Logger;
import main.java.logger.LoggerCollection;

import org.junit.Before;
import org.junit.Test;

public class TestLoggerCollection {
	private final String ONE_LOGGER = "one logger";
	private final String OTHER_LOGGER = "other logger";
	
	private Logger oneLogger = new Logger(ONE_LOGGER);
	private Logger otherLogger = new Logger(OTHER_LOGGER);

	@Before
	public void cleanUpCollection() {
	    LoggerCollection.getInstance().clearCollection();
	}

	@Test
	public void oneLoggerCollection() {
		LoggerCollection.getInstance().addLogger(oneLogger);
		assertEquals(oneLogger, LoggerCollection.getInstance().getLogger(ONE_LOGGER));
	}
	
	@Test
	public void twoLoggerCollection() {
		LoggerCollection.getInstance().addLogger(oneLogger);
		LoggerCollection.getInstance().addLogger(otherLogger);
		assertEquals(oneLogger, LoggerCollection.getInstance().getLogger(ONE_LOGGER));
		assertEquals(otherLogger, LoggerCollection.getInstance().getLogger(OTHER_LOGGER));
	}
	
	@Test(expected = NullPointerException.class)
	public void addNullLogger() {
		LoggerCollection.getInstance().addLogger(null);
	}
	
	@Test
	public void getInvalidLogger() {
		assertEquals(LoggerCollection.getInstance().getLogger("XYZ"),null);
	}

}
