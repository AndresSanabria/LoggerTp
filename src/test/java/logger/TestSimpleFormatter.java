package logger;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import logger.Level;
import logger.formatters.SimpleFormatter;

import org.junit.Test;

/**
 * The Class TestSimpleFormatter tests the SimpleFormatter.
 */
public class TestSimpleFormatter {

	/** The Constant DISTANCE_TEST_GIVE_FORMAT. */
	private static final Integer DISTANCE_TEST_GIVE_FORMAT = 2;

	/** The level values. */
	private enum levelValues { OFF, FATAL, ERROR, WARN, INFO, DEBUG }

	/** The Constant MESSAGE. */
	private static final String MESSAGE = "Este es mi mensaje";


	@Test
	public final void simpleFormatterContainingDate() {
		SimpleFormatter formatter = new SimpleFormatter("%d{HH:mm:ss} %n %m", DISTANCE_TEST_GIVE_FORMAT, "-");
		String formattedMsg = formatter.giveFormat(new Level(levelValues.WARN.name(), levelValues.WARN.ordinal()), MESSAGE);
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String dateStr = format.format(now);
		assertEquals(formattedMsg, dateStr + " - " + MESSAGE);
	}

	@Test
	public final void simpleFormatterContainingTwoDatesInDifferentFormat() {
		SimpleFormatter formatter = new SimpleFormatter("%d{HH:mm:ss} %n %m %n %d{h:mm a}", DISTANCE_TEST_GIVE_FORMAT, "-");
		String formattedMsg = formatter.giveFormat(new Level(levelValues.WARN.name(), levelValues.WARN.ordinal()), MESSAGE);
		Date now = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("h:mm a");
		String dateStr1 = format1.format(now);
		String dateStr2 = format2.format(now);
		assertEquals(formattedMsg, dateStr1 + " - " + MESSAGE + " - " + dateStr2);
	}

	@Test
	public final void simpleFormatterLogMessageAndSeparator() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m", DISTANCE_TEST_GIVE_FORMAT, ":");
		String formattedMsg = formatter.giveFormat(new Level(levelValues.WARN.name(), levelValues.WARN.ordinal()), MESSAGE);
		assertEquals(formattedMsg, "Test" + " : " + MESSAGE);
	}

	@Test
	public final void simpleFormatterContainingThreadName() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %t", DISTANCE_TEST_GIVE_FORMAT, "-");
		String formattedMsg = formatter.giveFormat(new Level(levelValues.WARN.name(), levelValues.WARN.ordinal()), MESSAGE);
		assertEquals(formattedMsg, "Test" + " - " + MESSAGE + " - " + Thread.currentThread().getName());
	}

	@Test
	public final void simpleFormatterWrittingLogLevel() {
		SimpleFormatter formatter = new SimpleFormatter("Test[%p]: %m", DISTANCE_TEST_GIVE_FORMAT, "-");
		String formattedMsg = formatter.giveFormat(new Level(levelValues.WARN.name(), levelValues.WARN.ordinal()), MESSAGE);
		assertEquals(formattedMsg, "Test[WARN]: " + MESSAGE);
	}

	@Test
	public final void simpleFormatterEscappingPercentSign() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %%", DISTANCE_TEST_GIVE_FORMAT, "-");
		String formattedMsg = formatter.giveFormat(new Level(levelValues.WARN.name(), levelValues.WARN.ordinal()), MESSAGE);
		assertEquals(formattedMsg, "Test" + " - " + MESSAGE + " - " + "%");
	}

	@Test
	public final void simpleFormatterCallerFileName() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %F", DISTANCE_TEST_GIVE_FORMAT, "-");
		String formattedMsg = formatter.giveFormat(new Level(levelValues.WARN.name(), levelValues.WARN.ordinal()), MESSAGE);
		assertEquals(formattedMsg, "Test" + " - " + MESSAGE + " - " + "TestSimpleFormatter.java");
	}

	@Test
	public final void simpleFormatterCallerMethodName() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %M", DISTANCE_TEST_GIVE_FORMAT, "-");
		String formattedMsg = formatter.giveFormat(new Level(levelValues.WARN.name(), levelValues.WARN.ordinal()), MESSAGE);
		assertEquals(formattedMsg, "Test" + " - " + MESSAGE + " - " + "simpleFormatterCallerMethodName");
	}

	@Test
	public final void simpleFormatterCallerLineNumber() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %L", DISTANCE_TEST_GIVE_FORMAT, "-");
		String formattedMsg = formatter.giveFormat(new Level(levelValues.WARN.name(), levelValues.WARN.ordinal()), MESSAGE);
		Integer lineNumber = Thread.currentThread().getStackTrace()[1].getLineNumber() - 1; //-1 pues give format esta en la linea anterior
		assertEquals(formattedMsg, "Test" + " - " + MESSAGE + " - " + lineNumber);
	}

	@Test
	public final void simpleFormatterWrittingLoggerName() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %g", DISTANCE_TEST_GIVE_FORMAT, "-", "LoggerName");
		String formattedMsg = formatter.giveFormat(new Level(levelValues.WARN.name(), levelValues.WARN.ordinal()), MESSAGE);
		assertEquals(formattedMsg, "Test" + " - " + MESSAGE + " - " + "LoggerName");
	}

	@Test
	public final void simpleFormatterWithoutMsg() {
		SimpleFormatter formatter = new SimpleFormatter("", "LoggerName", DISTANCE_TEST_GIVE_FORMAT);
		String formattedMsg = formatter.giveFormat(new Level(levelValues.WARN.name(), levelValues.WARN.ordinal()), MESSAGE);
		assertEquals(formattedMsg, "");
	}


}
