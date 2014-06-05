package logger.configurationReaders;

import static org.junit.Assert.*;

import java.io.IOException;

import logger.configurationReaders.Configuration;
import logger.configurationReaders.XMLFileReader;

import org.junit.Test;

public class TestXMLFileReader {
	
	@Test
	public void loadLevelFromXMLFile() throws IOException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config1.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLevel(), "INFO");
	}
	
	@Test
	public void loadMessageFormatFromXMLFile() throws IOException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config1.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getMessageFormat(), "%p %n %t %n %m");
	}
	
	@Test
	public void loadLogToFilesFromXMLFile() throws IOException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config1.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLogToFiles()[1], "log2.txt");
	}
	
	@Test
	public void loadCustomOutputsFromXMLFile() throws IOException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config1.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getCustomOutputs().get(1)[0], "logger.FileOutput");
	}
	
	@Test
	public void loadRexExFilterFromXMLFile() throws IOException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config4.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getRegExFilter(), ".*TRACE.*");
	}
	
	@Test
	public void loadCustomFilterFromXMLFile() throws IOException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config5.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getCustomFilter()[0], "logger.Filter");
	}
	
	@Test
	public void loadCustomOutputsMissingImplementorFromXMLFile() throws IOException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config2.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getCustomOutputs().size(), 0);
	}
	
	@Test
	public void loadMissingLogToFilesNodeFromXMLFile() throws IOException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config2.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLogToFiles().length, 0);
	}
	
	@Test
	public void loadMissingLevelNodeFromXMLFile() throws IOException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config2.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLevel(), "OFF");
	}
	
	@Test
	public void loadEmptyLevelNodeFromXMLFile() throws IOException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config3.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLevel(), "OFF");
	}
	
	@Test
	public void loadEmptyMessageFormatNodeFromXMLFile() throws IOException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config3.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getMessageFormat(), "");
	}
	
}
