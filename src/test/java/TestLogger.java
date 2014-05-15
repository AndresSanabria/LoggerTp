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
		Logger logger=new Logger(formatter, "DEBUG");
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
		Logger logger=new Logger(formatter, "DEBUG");
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
		Logger logger=new Logger(formatter, "DEBUG");
		logger.addFileOutput(path);
		logger.debug(msg);
		String text = "Test" + " - " + msg + " - " + "TestLogger.java";
		assertTrue(stringInFile(text,file));
	}
	
}