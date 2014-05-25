package test.java;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.logger.SimpleFormatter;


import org.junit.Test;

public class TestSimpleFormatter {
	
	final Integer DISTANCE_TEST_GIVE_FORMAT = 2;
	
	@Test
	public void simpleFormatterContainingDate() {
		SimpleFormatter formatter = new SimpleFormatter("%d{HH:mm:ss} %n %m", DISTANCE_TEST_GIVE_FORMAT, "-");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String date_string = format.format(now);
		assertEquals(formattedMsg,date_string + " - " + msg);
	}
	
	@Test
	public void simpleFormatterContainingTwoDatesInDifferentFormat() {
		SimpleFormatter formatter = new SimpleFormatter("%d{HH:mm:ss} %n %m %n %d{h:mm a}", DISTANCE_TEST_GIVE_FORMAT,"-");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		Date now = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("h:mm a");
		String date_string1 = format1.format(now);
		String date_string2 = format2.format(now);
		assertEquals(formattedMsg,date_string1 + " - " + msg + " - " + date_string2);
	}
	
	@Test
	public void simpleFormatterLogMessageAndSeparator() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m", DISTANCE_TEST_GIVE_FORMAT,":");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		assertEquals(formattedMsg,"Test" + " : " + msg);
	}
	
	@Test
	public void simpleFormatterContainingThreadName() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %t", DISTANCE_TEST_GIVE_FORMAT,"-");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		assertEquals(formattedMsg,"Test" + " - " + msg + " - " + Thread.currentThread().getName());
	}
	
	@Test
	public void simpleFormatterWrittingLogLevel() {
		SimpleFormatter formatter = new SimpleFormatter("Test[%p]: %m", DISTANCE_TEST_GIVE_FORMAT,"-");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		assertEquals(formattedMsg,"Test[WARN]: " + msg);
	}	
	
	@Test
	public void simpleFormatterEscappingPercentSign() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %%", DISTANCE_TEST_GIVE_FORMAT,"-");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		assertEquals(formattedMsg,"Test" + " - " + msg + " - " + "%");
	}	
	
	@Test
	public void simpleFormatterCallerFileName() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %F", DISTANCE_TEST_GIVE_FORMAT,"-");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		assertEquals(formattedMsg, "Test" + " - " + msg + " - " + "TestSimpleFormatter.java");
	}	
	
	@Test
	public void simpleFormatterCallerMethodName() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %M", DISTANCE_TEST_GIVE_FORMAT,"-");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		assertEquals(formattedMsg, "Test" + " - " + msg + " - " + "simpleFormatterCallerMethodName");
	}
	
	@Test
	public void simpleFormatterCallerLineNumber() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %L", DISTANCE_TEST_GIVE_FORMAT,"-");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		Integer lineNumber = Thread.currentThread().getStackTrace()[1].getLineNumber()-1; //-1 pues give format esta en la linea anterior
		assertEquals(formattedMsg, "Test" + " - " + msg + " - " + lineNumber);
	}
	
	@Test
	public void simpleFormatterWithoutMsg() {
		SimpleFormatter formatter = new SimpleFormatter("", DISTANCE_TEST_GIVE_FORMAT,"");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		assertEquals(formattedMsg,"");
	}	
	
	

}