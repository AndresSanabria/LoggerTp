package logger.configurationReaders;

import static org.junit.Assert.*;

import logger.configurationReaders.Configuration;
import logger.configurationReaders.PropertiesFileReader;

import org.junit.Test;

/**
 * The Class TestPropertiesFileReader tests the .properties file reader.
 */
public class TestPropertiesFileReader {

	@Test
	public final void loadLevelFromPropertiesFile() throws ReaderException {
		PropertiesFileReader reader = new PropertiesFileReader("configFiles/testPropertiesFileReader/config1.properties");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLevel(), "INFO");
	}

	@Test
	public final void loadMessageFormatFromPropertiesFile() throws ReaderException {
		PropertiesFileReader reader = new PropertiesFileReader("configFiles/testPropertiesFileReader/config1.properties");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getMessageFormat(), "%p %n %m");
	}

	@Test
	public final void loadLogToFilesFromPropertiesFile() throws ReaderException {
		PropertiesFileReader reader = new PropertiesFileReader("configFiles/testPropertiesFileReader/config1.properties");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLogToFiles()[1], "log2.txt");
	}

	@Test
	public final void loadCustomOutputsFromPropertiesFile() throws ReaderException {
		PropertiesFileReader reader = new PropertiesFileReader("configFiles/testPropertiesFileReader/config1.properties");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLogToFiles()[1], "log2.txt");
	}

	@Test
	public final void loadRegExFilterFromPropertiesFile() throws ReaderException {
		PropertiesFileReader reader = new PropertiesFileReader("configFiles/testPropertiesFileReader/config3.properties");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getRegExFilter(), ".*TRACE.*");
	}

	@Test
	public final void loadCustomFilterFromPropertiesFile() throws ReaderException {
		PropertiesFileReader reader = new PropertiesFileReader("configFiles/testPropertiesFileReader/config4.properties");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getCustomFilter()[0], "logger.Filter");
	}

	@Test
	public final void loadMissingLogToFilesFromPropertiesFile() throws ReaderException {
		PropertiesFileReader reader = new PropertiesFileReader("configFiles/testPropertiesFileReader/config2.properties");
		Configuration config = reader.readConfiguration();
		assertNull(config.getLogToFiles());
	}

	@Test
	public final void loadEmptyLevelFromPropertiesFile() throws ReaderException {
		PropertiesFileReader reader = new PropertiesFileReader("configFiles/testPropertiesFileReader/config2.properties");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLevel(), "OFF");
	}

}
