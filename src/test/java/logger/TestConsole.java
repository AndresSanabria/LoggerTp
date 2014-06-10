package logger;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import logger.outputs.ConsoleOutput;
import logger.outputs.Output;
import logger.outputs.OutputException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The Class TestConsole tests the Console output.
 */
public class TestConsole {

	/** The output content. */
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	/** The error content. */
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public final void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public final void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}

	@Test
	public final void writeString() throws OutputException {
		String text = "test";
		Output console = new ConsoleOutput();
		console.write(text);
		assertTrue(outContent.toString().contains(text));
	}

	@Test
	public final void writeTwoStrings() throws OutputException {
		String text1 = "test1";
		String text2 = "test2";
		Output console = new ConsoleOutput();
		console.write(text1);
		console.write(text2);
		assertTrue(outContent.toString().contains(text1));
		assertTrue(outContent.toString().contains(text2));
	}

	@Test
	public final void writeNullString() throws OutputException {
		String text = null;
		Output console = new ConsoleOutput();
		console.write(text);
		assertTrue(outContent.toString().contains(ConsoleOutput.INVALID_STRING));
	}

}
