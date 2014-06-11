package logger.configurationReaders;

import static org.junit.Assert.*;

import logger.configurationReaders.Configuration;
import logger.configurationReaders.XMLFileReader;

import org.junit.Test;

/**
 * The Class TestXMLFileReader tests the .xml file reader.
 */
public class TestXMLFileReader {

	@Test
	public final void loadLevelFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config1.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLevel(), "INFO");
	}

	@Test
	public final void loadMessageFormatFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config1.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getMessageFormat(), "%p %n %t %n %m");
	}

	@Test
	public final void loadLogToFilesFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config1.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLogToFiles()[1], "log2.txt");
	}

	@Test
	public final void loadCustomOutputsFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config1.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getCustomOutputs().get(1)[0], "logger.FileOutput");
	}

	@Test
	public final void loadRexExFilterFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config4.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getRegExFilter(), ".*TRACE.*");
	}

	@Test
	public final void loadEmptyRexExFilterFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config5.xml");
		Configuration config = reader.readConfiguration();
		assertNull(config.getRegExFilter());
	}

	@Test
	public final void loadCustomFilterFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config5.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getCustomFilter()[0], "logger.Filter");
	}

	@Test
	public final void loadMissingCustomFilterNodeFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config7.xml");
		Configuration config = reader.readConfiguration();
		assertNull(config.getCustomFilter());
	}

	@Test
	public final void loadCustomFilterMissingImplementorFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config4.xml");
		Configuration config = reader.readConfiguration();
		assertNull(config.getCustomFilter());
	}

	@Test
	public final void loadCustomFilterEmptyImplementorFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config6.xml");
		Configuration config = reader.readConfiguration();
		assertNull(config.getCustomFilter());
	}

	@Test
	public final void loadCustomOutputsMissingImplementorFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config2.xml");
		Configuration config = reader.readConfiguration();
		assertNull(config.getCustomOutputs());
	}

	@Test
	public final void loadCustomOutputsEmptyImplementorFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config3.xml");
		Configuration config = reader.readConfiguration();
		assertNull(config.getCustomOutputs());
	}

	@Test
	public final void loadMissingFilesNodeFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config2.xml");
		Configuration config = reader.readConfiguration();
		assertNull(config.getLogToFiles());
	}

	@Test
	public final void loadEmptyFilesNodeFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config2.xml");
		Configuration config = reader.readConfiguration();
		assertNull(config.getLogToFiles());
	}

	@Test
	public final void loadMissingOutputsNodeFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config5.xml");
		Configuration config = reader.readConfiguration();
		assertNull(config.getLogToFiles());
	}

	@Test
	public final void loadMissingLevelNodeFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config2.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLevel(), "OFF");
	}

	@Test
	public final void loadEmptyLevelNodeFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config3.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLevel(), "OFF");
	}

	@Test
	public final void loadMissingConsoleNodeFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config2.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLogToConsole(), false);
	}

	@Test
	public final void loadEmptyConsoleNodeFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config3.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getLogToConsole(), false);
	}

	@Test
	public final void loadEmptyMessageFormatNodeFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config3.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getMessageFormat(), "%p %n %t %n %m");
	}

	@Test
	public final void loadMissingMessageFormatNodeFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config5.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getMessageFormat(), "%p %n %t %n %m");
	}

	@Test
	public final void loadMissingMessageNodeFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config6.xml");
		Configuration config = reader.readConfiguration();
		assertEquals(config.getMessageFormat(), "%p %n %t %n %m");
		assertNull(config.getMessageSeparator());
	}

	@Test
	public final void loadMissingMessageSeparatorNodeFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config5.xml");
		Configuration config = reader.readConfiguration();
		assertNull(config.getMessageSeparator());
	}

	@Test
	public final void loadEmptyMessageSeparatorNodeFromXMLFile() throws ReaderException {
		XMLFileReader reader = new XMLFileReader("configFiles/testXMLFileReader/config3.xml");
		Configuration config = reader.readConfiguration();
		assertNull(config.getMessageSeparator());
	}

}
