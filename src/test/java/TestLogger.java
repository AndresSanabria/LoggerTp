package test.java;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import main.java.logger.Logger;
import main.java.logger.WriteException;

import org.junit.Test;

public class TestLogger {
	
	private static final String CONFIG_FILE_PATH = "configFiles/config.properties";
	private static final String MESSAGE = "Este es mi mensaje";
	private static final String LOG_PATH = "log.txt";
	private static final String DEFAULT_NAME = "name";
	private HelperForTests helper = new HelperForTests();
	
	
	@Test
	public void loggerCallerDistanceToGiveFormatMethodInStack() throws IOException, WriteException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = DEBUG\n" +
							"messageFormat = Test %n %m %n %F %n %M %n %L\n" +
							"messageSeparator = -\n" +
							"logToFiles = log.txt\n" +
							"logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = new Logger(DEFAULT_NAME);
		logger.debug(MESSAGE);
		Integer lineNumber = Thread.currentThread().getStackTrace()[1].getLineNumber()-1;//-1 pues el logger give format esta en la linea anterior
		String text = "Test" + " - " + MESSAGE + " - "+ "TestLogger.java" + " - " + "loggerCallerDistanceToGiveFormatMethodInStack" + " - " + lineNumber;
		assertTrue(helper.stringInFile(text,file));
	}
	
	@Test
	public void logInLevelDebug() throws IOException, WriteException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = DEBUG\n" +
							"messageFormat = Test %n %p %n %m\n" +
							"messageSeparator = -\n" +
							"logToFiles = log.txt\n" +
							"logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = new Logger(DEFAULT_NAME);
		logger.debug(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text1,file) && helper.stringInFile(text2,file));
	}
	
	@Test
	public void logInLevelInfo() throws IOException, WriteException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = INFO\n" +
							"messageFormat = Test %n %p %n %m\n" +
							"messageSeparator = -\n" +
							"logToFiles = log.txt\n" +
							"logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = new Logger(DEFAULT_NAME);
		logger.debug(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(!helper.stringInFile(text1,file) && helper.stringInFile(text2,file));
	}
	
	@Test
	public void logInLevelOff() throws IOException, WriteException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = OFF\n" +
							"messageFormat = Test %n %p %n %m\n" +
							"messageSeparator = -\n" +
							"logToFiles = log.txt\n" +
							"logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = new Logger(DEFAULT_NAME);
		logger.debug(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(!helper.stringInFile(text1,file) && !helper.stringInFile(text2,file));
	}
	
	@Test
	public void logInLevelTrace() throws IOException, WriteException {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = TRACE\n" +
							"messageFormat = Test %n %p %n %m\n" +
							"messageSeparator = -\n" +
							"logToFiles = log.txt\n" +
							"logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		Logger logger = new Logger(DEFAULT_NAME);
		logger.trace(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - TRACE - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text1,file) && helper.stringInFile(text2,file));
	}
	
}