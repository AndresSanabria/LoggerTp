package test.java;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.logger.SimpleFormatter;


import org.junit.Test;

public class TestSimpleFormatter {
	

	@Test
	public void testSimpleFormatterContainingDate() {
		SimpleFormatter formatter = new SimpleFormatter("%d{HH:mm:ss} %n %m","-");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String date_string = format.format(now);
		assertEquals(formattedMsg,date_string + " - " + msg);
	}
	
	@Test
	public void testSimpleFormatterContainingTwoDatesInDifferentFormat() {
		SimpleFormatter formatter = new SimpleFormatter("%d{HH:mm:ss} %n %m %n %d{h:mm a}","-");
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
	public void testSimpleFormatterLogMessageAndSeparator() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m",":");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		assertEquals(formattedMsg,"Test" + " : " + msg);
	}
	
	@Test
	public void testSimpleFormatterContainingThreadName() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %t","-");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		assertEquals(formattedMsg,"Test" + " - " + msg + " - " + Thread.currentThread().getName());
	}
	
	@Test
	public void testSimpleFormatterWrittingLogLevel() {
		SimpleFormatter formatter = new SimpleFormatter("Test[%p]: %m","-");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		assertEquals(formattedMsg,"Test[WARN]: " + msg);
	}	
	
	@Test
	public void testSimpleFormatterEscappingPorciento() {
		SimpleFormatter formatter = new SimpleFormatter("Test %n %m %n %%","-");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		assertEquals(formattedMsg,"Test" + " - " + msg + " - " + "%");
	}	
	
	@Test
	public void testSimpleFormatterWithoutMsg() {
		SimpleFormatter formatter = new SimpleFormatter("","");
		String msg = "Este es mi mensaje";
		String formattedMsg = formatter.giveFormat("WARN", msg);
		assertEquals(formattedMsg,"");
	}	
	
	

}