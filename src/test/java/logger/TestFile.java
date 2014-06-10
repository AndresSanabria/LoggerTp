package logger;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import logger.outputs.FileOutput;
import logger.outputs.Output;
import logger.outputs.OutputException;

import org.junit.Test;

/**
 * The Class TestFile tests the file output.
 */
public class TestFile {

	/** The helper. */
	private HelperForTests helper = new HelperForTests();


	@Test
	public final void writeOneString() throws OutputException, IOException {
		String path = "log.txt";
		String text = "test";
		File file = new File(path);
		file.delete();
		Output fileOutput = new FileOutput(path);
		fileOutput.write(text);
		assertTrue(helper.stringInFile(text, file));
	}

	@Test
	public final void writeTwoStrings() throws OutputException, IOException {
		String path = "log.txt";
		File file = new File(path);
		file.delete();
		String text1 = "test1";
		String text2 = "test2";
		Output fileOutput = new FileOutput(path);
		fileOutput.write(text1);
		fileOutput.write(text2);
		assertTrue(helper.stringInFile(text1, file));
		assertTrue(helper.stringInFile(text2, file));
	}

	@Test(expected = NullPointerException.class)
	public final void nullFile() throws IOException {
		new FileOutput(null);
		fail("Did not throw exception");
	}

}
