package test.java;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import main.java.logger.Logger;
import main.java.logger.SimpleFormatter;
import main.java.logger.WriteException;


import org.junit.Test;


public class TestLogger {
	
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
	public void testLoggerCallingLineNumber()throws IOException,WriteException {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %L","-");
		String path = "log.txt";
		File file = new File(path);
		file.delete();
		String msg = "Este es mi mensaje";
		Logger logger = new Logger(formatter, "DEBUG");
		logger.addFileOutput(path);
		logger.debug(msg);
		Integer lineNumber = Thread.currentThread().getStackTrace()[1].getLineNumber()-1;//-1 pues el logger give format esta en la linea anterior
		String text = "Test" + " - " + msg + " - " + lineNumber;
		assertTrue(stringInFile(text,file));
	}
	
	@Test
	public void testLoggerCallingMethodNameLog() throws IOException,WriteException {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %M","-");
		String path = "log.txt";
		File file = new File(path);
		file.delete();
		String msg = "Este es mi mensaje";
		Logger logger = new Logger(formatter, "DEBUG");
		logger.addFileOutput(path);
		logger.debug(msg);
		String text = "Test" + " - " + msg + " - " + "testLoggerCallingMethodNameLog";
		assertTrue(stringInFile(text,file));
	}
	
	@Test
	public void testLoggerCallingFileName() throws IOException,WriteException {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %F","-");
		String path = "log.txt";
		File file = new File(path);
		file.delete();
		String msg = "Este es mi mensaje";
		Logger logger = new Logger(formatter, "DEBUG");
		logger.addFileOutput(path);
		logger.debug(msg);
		String text = "Test" + " - " + msg + " - " + "TestLogger.java";
		assertTrue(stringInFile(text,file));
	}
	
	@Test
	public void testLogInLevelDebug() throws IOException,WriteException {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %p %n %m","-");
		String path = "log.txt";
		File file = new File(path);
		file.delete();
		String msg = "Este es mi mensaje";
		Logger logger = new Logger(formatter, "DEBUG");
		logger.addFileOutput(path);
		logger.debug(msg);
		logger.info(msg);
		String text1 = "Test" + " - DEBUG - " + msg;
		String text2 = "Test" + " - INFO - " + msg;
		assertTrue(stringInFile(text1,file) && stringInFile(text2,file));
	}
	
	@Test
	public void testLogInLevelInfo() throws IOException,WriteException {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %p %n %m","-");
		String path = "log.txt";
		File file = new File(path);
		file.delete();
		String msg = "Este es mi mensaje";
		Logger logger = new Logger(formatter, "INFO");
		logger.addFileOutput(path);
		logger.debug(msg);
		logger.info(msg);
		String text1 = "Test" + " - DEBUG - " + msg;
		String text2 = "Test" + " - INFO - " + msg;
		assertTrue(!stringInFile(text1,file) && stringInFile(text2,file));
	}
	
	@Test
	public void testLogInLevelOff() throws IOException,WriteException {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %p %n %m","-");
		String path = "log.txt";
		File file = new File(path);
		file.delete();
		String msg = "Este es mi mensaje";
		Logger logger = new Logger(formatter, "OFF");
		logger.addFileOutput(path);
		logger.debug(msg);
		logger.info(msg);
		String text1 = "Test" + " - DEBUG - " + msg;
		String text2 = "Test" + " - INFO - " + msg;
		assertTrue(!stringInFile(text1,file) && !stringInFile(text2,file));
	}
	
	@Test
	public void testSettingLoggerOffAndOn() throws IOException,WriteException {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %p %n %m","-");
		String path = "log.txt";
		File file = new File(path);
		file.delete();
		String msg = "Este es mi mensaje";
		Logger logger = new Logger(formatter, "DEBUG");
		logger.addFileOutput(path);
		logger.debug(msg);
		logger.off();
		logger.error(msg);
		logger.on();
		logger.fatal(msg);
		String text1 = "Test" + " - DEBUG - " + msg;
		String text2 = "Test" + " - ERROR - " + msg;
		String text3 = "Test" + " - FATAL - " + msg;
		assertTrue(stringInFile(text1,file) && !stringInFile(text2,file) && stringInFile(text3,file));
	}
	
}