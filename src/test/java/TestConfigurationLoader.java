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
		assertEquals(config.getMessageFormat(), "%p %n %m");
	}
	
	@Test
	public void testLoadLogToFilesFromConfiguration() {
		ConfigurationLoader config = new ConfigurationLoader();
		config.loadConfiguration("testConfig.properties");
		assertEquals(config.getLogToFiles()[1], "log2.txt");
	}
	
	@Test
	public void testLoadMissingLogToFilesFromConfiguration() {
		ConfigurationLoader config = new ConfigurationLoader();
		config.loadConfiguration("testConfig2.properties");
		assertEquals(config.getLogToFiles()[0], "");
	}
	
}
