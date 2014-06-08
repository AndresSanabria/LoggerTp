package logger;

import static org.junit.Assert.*;
import logger.formatters.JsonFormatter;

import org.junit.Test;

/**
 * The Class TestJsonFormatter tests the Json formatter.
 */
public class TestJsonFormatter {

	/** The level values. */
	private enum levelValues { OFF, FATAL, ERROR, WARN, INFO, DEBUG }

	/** The Constant MESSAGE. */
	private static final String MESSAGE = "Este es mi mensaje";


	@Test
	public final void jsonFormatterData() {
		JsonFormatter formatter = new JsonFormatter("Logger");
		String formattedMsg = formatter.giveFormat(new Level(levelValues.WARN.name(), levelValues.WARN.ordinal()), MESSAGE);
		assertTrue(formattedMsg.contains("{\"datetime\":\""));
		assertTrue(formattedMsg.contains("\",\"level\":\"" + levelValues.WARN.name() + "\",\"logger\":\"Logger\",\"message\":\"" + MESSAGE + "\"}"));
	}

}
