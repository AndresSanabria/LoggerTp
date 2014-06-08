package logger;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import logger.writables.FileOutput;
import logger.writables.Writable;
import logger.writables.WriteException;

import org.junit.Test;

public class TestFile {

	private HelperForTests helper = new HelperForTests();
	
	@Test
	public void writeOneString() throws WriteException, IOException {
		String path = "log.txt";
		String text = "test";
		File file = new File(path);
		file.delete();
		Writable fileOutput = new FileOutput(path);
		fileOutput.write(text);		
		assertTrue(helper.stringInFile(text, file));
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
		assertTrue(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}
	
	@Test(expected = NullPointerException.class)
	public void nullFile() throws IOException {
		new FileOutput(null);
		fail("Did not throw exception");
	}

}
