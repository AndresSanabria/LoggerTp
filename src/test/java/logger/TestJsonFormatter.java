package logger;

import static org.junit.Assert.*;
import logger.formatters.JsonFormatter;

import org.junit.Test;

public class TestJsonFormatter {
	
	private enum levelValues {
		OFF, FATAL, ERROR, WARN, INFO, DEBUG
	}
	private static final String MESSAGE = "Este es mi mensaje";

	@Test
	public void jsonFormatterData() {
		JsonFormatter formatter = new JsonFormatter("Logger");
		String formattedMsg = formatter.giveFormat(new Level(levelValues.WARN.name(), levelValues.WARN.ordinal()), MESSAGE);
		assertTrue(formattedMsg.contains("{\"datetime\":\""));
		assertTrue(formattedMsg.contains("\",\"level\":\"" + levelValues.WARN.name() + "\",\"logger\":\"Logger\",\"message\":\"" + MESSAGE + "\"}"));
	}
	
}
