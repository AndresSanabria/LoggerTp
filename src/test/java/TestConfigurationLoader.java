package test.java;

import static org.junit.Assert.*;
import main.java.logger.ConfigurationLoader;

import org.junit.Test;


public class TestConfigurationLoader {

	private static final String TEST_FILES_PATH = "configFiles/testConfigurationLoader/";
	
	@Test
	public void loadLevelFromConfiguration() {
		ConfigurationLoader config = new ConfigurationLoader(TEST_FILES_PATH + "testConfig.properties");
		assertEquals(config.getLevel(), "INFO");
	}
	
	@Test
	public void loadMessageFormatLevelFromConfiguration() {
		ConfigurationLoader config = new ConfigurationLoader(TEST_FILES_PATH + "testConfig.properties");
		assertEquals(config.getMessageFormat(), "%p %n %m");
	}
	
	@Test
	public void loadLogToFilesFromConfiguration() {
		ConfigurationLoader config = new ConfigurationLoader(TEST_FILES_PATH + "testConfig.properties");
		assertEquals(config.getLogToFiles()[1], "log2.txt");
	}
	
	@Test
	public void loadMissingLogToFilesFromConfiguration() {
		ConfigurationLoader config = new ConfigurationLoader(TEST_FILES_PATH + "testConfig2.properties");
		assertEquals(config.getLogToFiles()[0], "");
	}
	
}
