package logger;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import logger.Logger;
import logger.writables.WriteException;

import org.junit.Test;

/**
 * The Class TestLogger tests the Logger.
 */
public class TestLogger {

	/** The Constant CONFIG_FILE_PATH. */
	private static final String CONFIG_FILE_PATH = "configFiles/config.properties";

	/** The Constant MESSAGE. */
	private static final String MESSAGE = "Este es mi mensaje";

	/** The Constant LOG_PATH. */
	private static final String LOG_PATH = "log.txt";

	/** The Constant DEFAULT_NAME. */
	private static final String DEFAULT_NAME = "name";

	/** The helper. */
	private HelperForTests helper = new HelperForTests();


	@Test
	public final void loggerCallerDistanceToGiveFormatMethodInStack() throws IOException, WriteException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = DEBUG\n"
							+ "messageFormat = Test %n %m %n %F %n %M %n %L\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = new Logger(DEFAULT_NAME);
		logger.debug(MESSAGE);
		Integer lineNumber = Thread.currentThread().getStackTrace()[1].getLineNumber() - 1; //-1 pues el logger give format esta en la linea anterior
		String text = "Test" + " - " + MESSAGE + " - " + "TestLogger.java" + " - " + "loggerCallerDistanceToGiveFormatMethodInStack" + " - " + lineNumber;
		assertTrue(helper.stringInFile(text, file));
	}

	@Test
	public final void logInLevelDebug() throws IOException, WriteException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = DEBUG\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = new Logger(DEFAULT_NAME);
		logger.debug(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text1, file) && helper.stringInFile(text2, file));
	}

	@Test
	public final void logInLevelInfo() throws IOException, WriteException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = INFO\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = new Logger(DEFAULT_NAME);
		logger.debug(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(!helper.stringInFile(text1, file) && helper.stringInFile(text2, file));
	}

	@Test
	public final void logInLevelOff() throws IOException, WriteException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = OFF\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = new Logger(DEFAULT_NAME);
		logger.debug(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(!helper.stringInFile(text1, file) && !helper.stringInFile(text2, file));
	}

	@Test
	public final void logInLevelTrace() throws IOException, WriteException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = TRACE\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = new Logger(DEFAULT_NAME);
		logger.trace(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - TRACE - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text1, file) && helper.stringInFile(text2, file));
	}

}
