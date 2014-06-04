package logger;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

public class TestCustomOutput {
	
	private static String FILE_CLASS = FileOutput.class.getName();
	private HelperForTests helper = new HelperForTests();
	private OutputFactory factory = new OutputFactory();

	@Test
	public void createCustomFileOutput() throws CustomOutputException, WriteException, FileNotFoundException {
		String[] params = new String[1];
		params[0] = "log.txt";
		Writable output = factory.createCustomOutput(FILE_CLASS, params);
		output.write("chau");
		File file = new File("log.txt");
		assertTrue(helper.stringInFile("chau", file));
		file.delete();
	}

}
