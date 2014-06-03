package test.java;

import static org.junit.Assert.*;

import java.io.IOException;

import main.java.logger.ConfigurationLoader;
import main.java.logger.WriteException;

import org.junit.Test;


public class TestConfigurationLoader {
	
	private static final String CONFIG_FILE_PATH = "configFiles/config.properties";
	
	private HelperForTests helper = new HelperForTests();
	
	
	@Test
	public void loadLevelFromConfiguration() throws IOException, WriteException {
		String textFile =	"level = INFO\n" +
							"messageFormat = %p %n %m\n" +
							"messageSeparator = -\n" +
							"logToFiles = log.txt;log2.txt\n" +
							"logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		ConfigurationLoader config = new ConfigurationLoader();
		assertEquals(config.getConfiguration().getLevel(), "INFO");
	}
	
	@Test
	public void loadMessageFormatLevelFromConfiguration() throws IOException, WriteException {
		String textFile =	"level = INFO\n" +
							"messageFormat = %p %n %m\n" +
							"messageSeparator = -\n" +
							"logToFiles = log.txt;log2.txt\n" +
							"logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		ConfigurationLoader config = new ConfigurationLoader();
		assertEquals(config.getConfiguration().getMessageFormat(), "%p %n %m");
	}
	
	@Test
	public void loadLogToFilesFromConfiguration() throws IOException, WriteException {
		String textFile =	"level = INFO\n" +
							"messageFormat = %p %n %m\n" +
							"messageSeparator = -\n" +
							"logToFiles = log.txt;log2.txt\n" +
							"logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		ConfigurationLoader config = new ConfigurationLoader();
		assertEquals(config.getConfiguration().getLogToFiles()[1], "log2.txt");
	}
	
	@Test
	public void loadMissingLogToFilesFromConfiguration() throws IOException, WriteException {
		String textFile =	"level = INFO\n" +
							"messageFormat = %p %n %m\n" +
							"messageSeparator = -\n" +
							"logToConsole = false";
		this.helper.writeNewFileWithText(CONFIG_FILE_PATH, textFile);
		ConfigurationLoader config = new ConfigurationLoader();
		assertEquals(config.getConfiguration().getLogToFiles()[0], "");
	}
	
}
