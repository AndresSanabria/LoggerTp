package logger;

import static org.junit.Assert.*;
import logger.LoggerSLF4JFactory;

import org.junit.Test;
import org.slf4j.Logger;

/**
 * The Class TestLoggerSLF4JFactory tests the LoggerSLF4JFactory.
 */
public class TestLoggerSLF4JFactory{

	@Test
	public final void loggerSLF4FFactoryGetNonExistingLoggerReturnsNewLogger() {
		LoggerSLF4JFactory factory = new LoggerSLF4JFactory();
		Logger logger = factory.getLogger("My Logger");
		assertEquals(logger, factory.getLogger("My Logger"));
	}

}
