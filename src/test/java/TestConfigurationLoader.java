package test.java;

import static org.junit.Assert.*;
import main.java.logger.ConfigurationLoader;

import org.junit.Test;

public class TestConfigurationLoader {

	@Test
	public void loadLevelFromConfiguration() {
		ConfigurationLoader config = new ConfigurationLoader();
		config.loadConfiguration("testConfig.properties");
		assertEquals(config.getLevel(), "INFO");
	}
	
	@Test
	public void loadMessageFormatLevelFromConfiguration() {
		ConfigurationLoader config = new ConfigurationLoader();
		config.loadConfiguration("testConfig.properties");
		assertEquals(config.getMessageFormat(), "%p %n %m");
	}
	
	@Test
	public void loadLogToFilesFromConfiguration() {
		ConfigurationLoader config = new ConfigurationLoader();
		config.loadConfiguration("testConfig.properties");
		assertEquals(config.getLogToFiles()[1], "log2.txt");
	}
	
	@Test
	public void loadMissingLogToFilesFromConfiguration() {
		ConfigurationLoader config = new ConfigurationLoader();
		config.loadConfiguration("testConfig2.properties");
		assertEquals(config.getLogToFiles()[0], "");
	}
	
}
