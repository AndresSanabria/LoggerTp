package test.java;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import main.java.logger.FileOutput;
import main.java.logger.Writable;
import main.java.logger.WriteException;

import org.junit.Test;

public class TestFile {
	
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
	public void writeOneString() throws WriteException, IOException {
		String path = "log.txt";
		String text = "test";
		File file = new File(path);
		file.delete();
		Writable fileOutput = new FileOutput(path);
		fileOutput.write(text);		
		assertTrue(stringInFile(text,file));
	}
	
	@Test
	public void writeTwoStrings() throws WriteException, IOException {
		String path = "log.txt";
		File file = new File(path);
		file.delete();
		String text1 = "test1";
		String text2 = "test2";
		Writable fileOutput = new FileOutput(path);
		fileOutput.write(text1);
		fileOutput.write(text2);
		assertTrue(stringInFile(text1,file));
		assertTrue(stringInFile(text2,file));
	}
	
	@Test(expected = NullPointerException.class)
	public void nullFile() throws IOException {
		new FileOutput(null);
		fail("Did not throw exception");
	}

}
