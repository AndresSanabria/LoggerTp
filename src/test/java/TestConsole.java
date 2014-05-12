package test.java;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import main.java.logger.Console;
import main.java.logger.Writable;

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
	public void writeString() {
		String text = "test";
		Writable console = new Console();
		console.write(text);
		assertTrue(outContent.toString().contains(text));
	}
	
	@Test
	public void writeTwoStrings() {
		String text1 = "test1";
		String text2 = "test2";
		Writable console = new Console();
		console.write(text1);
		console.write(text2);
		assertTrue(outContent.toString().contains(text1));
		assertTrue(outContent.toString().contains(text2));
	}
	
	@Test
	public void writeNullString() {
		String text = null;
		Writable console = new Console();
		console.write(text);
		assertTrue(outContent.toString().contains(Console.INVALID_STRING));
	}

}
