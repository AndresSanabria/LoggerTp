package logger;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import logger.outputs.OutputException;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * The Class TestSLF4JLogger tests SLF4J.
 */
public class TestLoggerSLF4J{
	
	/** The Constant CONFIG_FILE_PATH. */
	private static final String CONFIG_FILE_PATH = "configFiles/config.properties";
	
	/** The helper. */
	private HelperForTests helper = new HelperForTests();
	
	/** The Constant MESSAGE. */
	private static final String MESSAGE = "Este es mi mensaje";
	
	/** The Constant LOG_PATH. */
	private static final String LOG_PATH = "log.txt";
	
	/** The Logger factory. */
	private ILoggerFactory factory;
	
	/** The error content. */
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public final void setUpStreams() {
	    System.setErr(new PrintStream(errContent));
	}

	@Before
	public final void setUp() {
		factory = StaticLoggerBinder.getSingleton().getLoggerFactory();
	}
	
	@Test
	public final void logInLevelDebug() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = DEBUG\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Debug Logger");
		logger.debug(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelDebugWithArg() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = DEBUG\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Debug Logger");
		logger.debug(MESSAGE, "");
		logger.info(MESSAGE, "");
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelDebugWith2Arg() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = DEBUG\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Debug Logger");
		logger.debug(MESSAGE, "", "");
		logger.info(MESSAGE, "", "");
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelDebugWith3Arg() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = DEBUG\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Debug Logger");
		logger.debug(MESSAGE, "", "", "");
		logger.info(MESSAGE, "", "", "");
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelInfo() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = INFO\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Info Logger");
		logger.debug(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertFalse(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelInfoWithArg() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = INFO\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Info Logger");
		logger.debug(MESSAGE, "");
		logger.info(MESSAGE, "");
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertFalse(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelInfoWith2Arg() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = INFO\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Info Logger");
		logger.debug(MESSAGE, "", "");
		logger.info(MESSAGE, "", "");
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertFalse(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelInfoWith3Arg() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = INFO\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Info Logger");
		logger.debug(MESSAGE, "", "", "");
		logger.info(MESSAGE, "", "", "");
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertFalse(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelTrace() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = TRACE\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Trace Logger");
		logger.trace(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - TRACE - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelTraceWithArg() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = TRACE\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Trace Logger");
		logger.trace(MESSAGE, "");
		logger.info(MESSAGE, "");
		String text1 = "Test" + " - TRACE - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelTraceWith2Arg() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = TRACE\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Trace Logger");
		logger.trace(MESSAGE, "", "");
		logger.info(MESSAGE, "", "");
		String text1 = "Test" + " - TRACE - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelTraceWith3Arg() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = TRACE\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Trace Logger");
		logger.trace(MESSAGE, "", "", "");
		logger.info(MESSAGE, "", "", "");
		String text1 = "Test" + " - TRACE - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelWarn() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = WARN\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Warn Logger");
		logger.debug(MESSAGE);
		logger.warn(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - WARN - " + MESSAGE;
		assertFalse(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelWarnWithArg() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = WARN\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Warn Logger");
		logger.debug(MESSAGE, "");
		logger.warn(MESSAGE, "");
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - WARN - " + MESSAGE;
		assertFalse(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelWarnWith2Arg() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = WARN\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Warn Logger");
		logger.debug(MESSAGE, "", "");
		logger.warn(MESSAGE, "", "");
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - WARN - " + MESSAGE;
		assertFalse(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelWarnWith3Arg() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = WARN\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Warn Logger");
		logger.debug(MESSAGE, "", "", "");
		logger.warn(MESSAGE, "", "", "");
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - WARN - " + MESSAGE;
		assertFalse(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelError() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = ERROR\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Error Logger");
		logger.debug(MESSAGE);
		logger.error(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - ERROR - " + MESSAGE;
		assertFalse(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelErrorWithArg() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = ERROR\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Error Logger");
		logger.debug(MESSAGE, "");
		logger.error(MESSAGE, "");
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - ERROR - " + MESSAGE;
		assertFalse(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelErrorWith2Arg() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = ERROR\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Error Logger");
		logger.debug(MESSAGE, "", "");
		logger.error(MESSAGE, "", "");
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - ERROR - " + MESSAGE;
		assertFalse(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelErrorWith3Arg() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = ERROR\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Error Logger");
		logger.debug(MESSAGE, "", "", "");
		logger.error(MESSAGE, "", "", "");
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - ERROR - " + MESSAGE;
		assertFalse(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test
	public final void logInLevelErrorWithExceptionError() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = ERROR\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = true";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Error Logger 2");
		OutputException e = new OutputException();
		File output_file = new File("log.txt");
		output_file.setReadOnly();
		logger.error(MESSAGE,e);
		output_file.delete();
		assertTrue(errContent.toString().contains("Error while logging:" + e + " Exception thrown"));
	}
	
	@Test
	public final void logInLevelWarnWithExceptionError() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = WARN\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = true";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Warn Logger 2");
		OutputException e = new OutputException();
		File output_file = new File("log.txt");
		output_file.setReadOnly();
		logger.warn(MESSAGE,e);
		output_file.delete();
		assertTrue(errContent.toString().contains("Error while logging:" + e + " Exception thrown"));
	}
	
	@Test
	public final void logInLevelTraceWithExceptionError() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = TRACE\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = true";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Trace Logger 2");
		OutputException e = new OutputException();
		File output_file = new File("log.txt");
		output_file.setReadOnly();
		logger.trace(MESSAGE,e);
		output_file.delete();
		assertTrue(errContent.toString().contains("Error while logging:" + e + " Exception thrown"));
	}
	
	@Test
	public final void logInLevelDebugWithExceptionError() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = DEBUG\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = true";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Debug Logger 2");
		OutputException e = new OutputException();
		File output_file = new File("log.txt");
		output_file.setReadOnly();
		logger.debug(MESSAGE,e);
		output_file.delete();
		assertTrue(errContent.toString().contains("Error while logging:" + e + " Exception thrown"));
	}
	
	@Test
	public final void logInLevelInfoWithExceptionError() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = INFO\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = true";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Info Logger 2");
		OutputException e = new OutputException();
		File output_file = new File("log.txt");
		output_file.setReadOnly();
		logger.info(MESSAGE,e);
		output_file.delete();
		assertTrue(errContent.toString().contains("Error while logging:" + e + " Exception thrown"));
	}
	
	@Test
	public final void shouldLogInDifferentLevels() throws OutputException, IOException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = WARN\n"
							+ "messageFormat = Test %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = true";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = factory.getLogger("SLF4J Warn Logger");
		assertTrue(logger.isErrorEnabled());
		assertTrue(logger.isWarnEnabled());
		assertFalse(logger.isDebugEnabled());
		assertFalse(logger.isInfoEnabled());
		assertFalse(logger.isTraceEnabled());
	}

}