package test.java;

import static org.junit.Assert.*;
import main.java.logger.Configuration;

import org.junit.Test;

public class TestConfiguration {

	@Test
	public void testLoadLevelFromConfiguration() {
		Configuration config = new Configuration();
		config.loadConfiguration("testConfig.properties");
		assertEquals(config.getLevel(), "INFO");
	}
	
	@Test
	public void testLoadMessageFormatLevelFromConfiguration() {
		Configuration config = new Configuration();
		config.loadConfiguration("testConfig.properties");
		assertEquals(config.getMessageFormat(), "%{HH:mm:ss} %n %p %n %t %n %m");
	}
	
	@Test
	public void testLoadLogToFilesFromConfiguration() {
		Configuration config = new Configuration();
		config.loadConfiguration("testConfig.properties");
		assertEquals(config.getLogToFiles()[1], "/home/log2.txt");
	}
	
}
