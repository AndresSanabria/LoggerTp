package test.java;

import static org.junit.Assert.*;
import main.java.logger.ConfigurationLoader;

import org.junit.Test;

public class TestConfigurationLoader {

	@Test
	public void testLoadLevelFromConfiguration() {
		ConfigurationLoader config = new ConfigurationLoader();
		config.loadConfiguration("testConfig.properties");
		assertEquals(config.getLevel(), "INFO");
	}
	
	@Test
	public void testLoadMessageFormatLevelFromConfiguration() {
		ConfigurationLoader config = new ConfigurationLoader();
		config.loadConfiguration("testConfig.properties");
		assertEquals(config.getMessageFormat(), "%{HH:mm:ss} %n %p %n %t %n %m");
	}
	
	@Test
	public void testLoadLogToFilesFromConfiguration() {
		ConfigurationLoader config = new ConfigurationLoader();
		config.loadConfiguration("testConfig.properties");
		assertEquals(config.getLogToFiles()[1], "/home/log2.txt");
	}
	
}
