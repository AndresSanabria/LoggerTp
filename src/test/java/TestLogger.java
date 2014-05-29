package test.java;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import main.java.logger.Logger;
import main.java.logger.WriteException;

import org.junit.Test;


public class TestLogger {
	
	private static final String TEST_FILES_PATH = "configFiles/testLogger/";
	private static final String MESSAGE = "Este es mi mensaje";
	private static final String LOG_PATH = "log.txt";
	private HelperForTests helper = new HelperForTests();
	
	@Test
	public void loggerCallerDistanceToGiveFormatMethodInStack()throws IOException,WriteException {
		File file = new File(LOG_PATH);
		file.delete();
		Logger logger = new Logger(TEST_FILES_PATH + "testLoggerCallerDistanceToGiveFormatMethodInStack.properties");
		logger.debug(MESSAGE);
		Integer lineNumber = Thread.currentThread().getStackTrace()[1].getLineNumber()-1;//-1 pues el logger give format esta en la linea anterior
		String text = "Test" + " - " + MESSAGE + " - "+ "TestLogger.java" + " - " + "loggerCallerDistanceToGiveFormatMethodInStack" + " - " + lineNumber;
		assertTrue(helper.stringInFile(text,file));
	}
	
	@Test
	public void logInLevelDebug() throws IOException,WriteException {
		File file = new File(LOG_PATH);
		file.delete();
		Logger logger = new Logger(TEST_FILES_PATH + "testLogInLevelDebug.properties");
		logger.debug(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text1,file) && helper.stringInFile(text2,file));
	}
	
	@Test
	public void logInLevelInfo() throws IOException,WriteException {
		File file = new File(LOG_PATH);
		file.delete();
		Logger logger = new Logger(TEST_FILES_PATH + "testLogInLevelInfo.properties");
		logger.debug(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(!helper.stringInFile(text1,file) && helper.stringInFile(text2,file));
	}
	
	@Test
	public void logInLevelOff() throws IOException,WriteException {
		File file = new File(LOG_PATH);
		file.delete();
		Logger logger = new Logger(TEST_FILES_PATH + "testLogInLevelOff.properties");
		logger.debug(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(!helper.stringInFile(text1,file) && !helper.stringInFile(text2,file));
	}
	
}