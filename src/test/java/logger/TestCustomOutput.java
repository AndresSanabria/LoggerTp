package logger;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import logger.writables.ConsoleOutput;
import logger.writables.FileOutput;
import logger.customFactory.CustomFactory;
import logger.customFactory.CustomOutputException;
import logger.writables.Writable;
import logger.writables.WriteException;

import org.junit.Test;

/**
 * The Class TestCustomOutput tests CustomOutput.
 */
public class TestCustomOutput {

	/** The helper. */
	private HelperForTests helper = new HelperForTests();

	/** The custom factory. */
	private CustomFactory factory = new CustomFactory();

	@Test
	public final void createCustomFileOutput() throws CustomOutputException, WriteException, FileNotFoundException {
		String[] params = new String[1];
		params[0] = "log.txt";
		Writable output = factory.createCustomOutput(FileOutput.class.getName(), params);
		output.write("chau");
		File file = new File("log.txt");
		assertTrue(helper.stringInFile("chau", file));
		file.delete();
	}

	@Test
	public final void createCustomConsoleOutput() throws CustomOutputException, WriteException {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		Writable output = factory.createCustomOutput(ConsoleOutput.class.getName(), new String[0]);
		output.write("hola");
		assertTrue(outContent.toString().contains("hola"));
		System.setOut(null);
	}

	@Test(expected = CustomOutputException.class)
	public final void customOutputNotImplementingWritable() throws CustomOutputException {
		factory.createCustomOutput(Level.class.getName(), new String[0]);
	}

}
