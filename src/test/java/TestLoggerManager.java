package test.java;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import main.java.logger.Logger;
import main.java.logger.LoggerManager;
import main.java.logger.WriteException;

import org.junit.Test;


public class TestLoggerManager {

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
	public void loadConfiguration() throws IOException, WriteException {
		String path = "log.txt";
		File file = new File(path);
		file.delete();
		LoggerManager manager = new LoggerManager();
		manager.loadConfiguration("testConfig.properties");
		Logger logger = manager.getLogger();
		String msg = "Este es mi mensaje";
		logger.debug(msg);
		logger.info(msg);
		String text1 = "DEBUG - " + msg;
		String text2 = "INFO - " + msg;
		assertTrue(!stringInFile(text1,file) && stringInFile(text2,file));
	}
	
}
