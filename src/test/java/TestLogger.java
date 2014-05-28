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
	
	//TODO: Method copy from TestFile.java, move somewhere else to reuse
	private boolean stringInFile(String text, File file) throws FileNotFoundException {
		Scanner fileScanner = new Scanner(file);
		boolean found = false;
		while(fileScanner.hasNextLine()){
		     if(text.equals(fileScanner.nextLine().trim())){
		        found = true;
		        break;
		      }
		 }
		fileScanner.close();
		return found;
	}
	
	@Test
	public void loggerCallerDistanceToGiveFormatMethodInStack()throws IOException,WriteException {
		String path = "log.txt";
		File file = new File(path);
		file.delete();
		Logger logger = new Logger(TEST_FILES_PATH + "testLoggerCallerDistanceToGiveFormatMethodInStack.properties");
		logger.debug(MESSAGE);
		Integer lineNumber = Thread.currentThread().getStackTrace()[1].getLineNumber()-1;//-1 pues el logger give format esta en la linea anterior
		String text = "Test" + " - " + MESSAGE + " - "+ "TestLogger.java" + " - " + "loggerCallerDistanceToGiveFormatMethodInStack" + " - " + lineNumber;
		assertTrue(stringInFile(text,file));
	}
	
	@Test
	public void logInLevelDebug() throws IOException,WriteException {
		String path = "log.txt";
		File file = new File(path);
		file.delete();
		Logger logger = new Logger(TEST_FILES_PATH + "testLogInLevelDebug.properties");
		logger.debug(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(stringInFile(text1,file) && stringInFile(text2,file));
	}
	
	@Test
	public void logInLevelInfo() throws IOException,WriteException {
		String path = "log.txt";
		File file = new File(path);
		file.delete();
		Logger logger = new Logger(TEST_FILES_PATH + "testLogInLevelInfo.properties");
		logger.debug(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(!stringInFile(text1,file) && stringInFile(text2,file));
	}
	
	@Test
	public void logInLevelOff() throws IOException,WriteException {
		String path = "log.txt";
		File file = new File(path);
		file.delete();
		Logger logger = new Logger(TEST_FILES_PATH + "testLogInLevelOff.properties");
		logger.debug(MESSAGE);
		logger.info(MESSAGE);
		String text1 = "Test" + " - DEBUG - " + MESSAGE;
		String text2 = "Test" + " - INFO - " + MESSAGE;
		assertTrue(!stringInFile(text1,file) && !stringInFile(text2,file));
	}
	
}