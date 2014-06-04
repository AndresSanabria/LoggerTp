package logger;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import logger.ConsoleOutput;
import logger.Writable;
import logger.WriteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestConsole {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}

	@Test
	public void writeString() throws WriteException {
		String text = "test";
		Writable console = new ConsoleOutput();
		console.write(text);
		assertTrue(outContent.toString().contains(text));
	}
	
	@Test
	public void writeTwoStrings() throws WriteException{
		String text1 = "test1";
		String text2 = "test2";
		Writable console = new ConsoleOutput();
		console.write(text1);
		console.write(text2);
		assertTrue(outContent.toString().contains(text1));
		assertTrue(outContent.toString().contains(text2));
	}
	
	@Test
	public void writeNullString() throws WriteException {
		String text = null;
		Writable console = new ConsoleOutput();
		console.write(text);
		assertTrue(outContent.toString().contains(ConsoleOutput.INVALID_STRING));
	}

}
