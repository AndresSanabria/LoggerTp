package logger;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import logger.ConfigurationLoader;
import logger.writables.WriteException;

import org.junit.Test;


public class TestConfigurationLoader {
	
	private static final String PROPERTIES_FILE_PATH = "configFiles/config.properties";
	
	private static final String XML_FILE_PATH = "configFiles/config.xml";
	
	private HelperForTests helper = new HelperForTests();
	
	
	@Test
	public void loadConfigurationFromPropertiesFileFirst() throws IOException, WriteException {
		String textFile =	"level = INFO\n" +
							"messageFormat = %p %n %m\n" +
							"messageSeparator = -\n" +
							"logToFiles = log.txt;log2.txt\n" +
							"logToConsole = false";
		this.helper.writeNewFileWithText(PROPERTIES_FILE_PATH, textFile);
		ConfigurationLoader config = new ConfigurationLoader();
		assertEquals(config.getConfiguration().getLevel(), "INFO");
	}
	
	@Test
	public void loadConfigurationFromXMLFileSecond() throws WriteException, IOException {
		File file = new File(PROPERTIES_FILE_PATH);
		file.delete();
		String textFile =	"<configuration>" +
							"<level>INFO</level>" +
							"<message><format>%p %n %m</format>" +
							"<separator>-</separator></message>" +
							"<outputs><logToFiles>log.txt;log2.txt</logToFiles>" +
							"<logToConsole>false</logToConsole></outputs>" +
							"</configuration>";
		this.helper.writeNewFileWithText(XML_FILE_PATH, textFile);
		ConfigurationLoader config = new ConfigurationLoader();
		assertEquals(config.getConfiguration().getLevel(), "INFO");
	}
	
	@Test
	public void loadConfigurationFromDefaultThird() {
		File propFile = new File(PROPERTIES_FILE_PATH);
		propFile.delete();
		File xmlFile = new File(XML_FILE_PATH);
		xmlFile.delete();
		ConfigurationLoader config = new ConfigurationLoader();
		assertEquals(config.getConfiguration().getLevel(), "OFF");
	}
	
}
