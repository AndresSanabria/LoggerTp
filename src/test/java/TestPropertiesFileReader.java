package test.java;

import static org.junit.Assert.*;

import java.io.IOException;

import main.java.logger.configurationReaders.Configuration;
import main.java.logger.configurationReaders.PropertiesFileReader;

import org.junit.Test;


public class TestPropertiesFileReader {
	
	@Test
	public void loadLevelFromPropertiesFile() throws IOException {
		PropertiesFileReader reader = new PropertiesFileReader("configFiles/testPropertiesFileReader/config1.properties");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLevel(), "INFO");
	}
	
	@Test
	public void loadMessageFormatFromPropertiesFile() throws IOException {
		PropertiesFileReader reader = new PropertiesFileReader("configFiles/testPropertiesFileReader/config1.properties");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getMessageFormat(), "%p %n %m");
	}
	
	@Test
	public void loadLogToFilesFromPropertiesFile() throws IOException {
		PropertiesFileReader reader = new PropertiesFileReader("configFiles/testPropertiesFileReader/config1.properties");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLogToFiles()[1], "log2.txt");
	}
	
	@Test
	public void loadMissingLogToFilesFromPropertiesFile() throws IOException {
		PropertiesFileReader reader = new PropertiesFileReader("configFiles/testPropertiesFileReader/config2.properties");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLogToFiles()[0], "");
	}
	
	@Test
	public void loadEmptyLevelFromXMLFile() throws IOException {
		PropertiesFileReader reader = new PropertiesFileReader("configFiles/testPropertiesFileReader/config2.properties");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLevel(), "OFF");
	}
	
}