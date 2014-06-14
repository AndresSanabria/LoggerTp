package logger;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import logger.ConfigurationLoader;
import logger.customFactory.CustomFilterException;
import logger.customFactory.CustomOutputException;
import logger.outputs.OutputException;
import logger.configurationReaders.ReaderException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The Class TestConfigurationLoader tests the ConfigurationLoader.
 */
public class TestConfigurationLoader {

	/** The Constant LOG_PATH. */
	private static final String LOG_PATH = "log.txt";

	/** The Constant MESSAGE. */
	private static final String MESSAGE = "Este es mi mensaje";

	/** The Constant PROPERTIES_FILE_PATH. */
	private static final String PROPERTIES_FILE_PATH = "configFiles/config.properties";

	/** The Constant XML_FILE_PATH. */
	private static final String XML_FILE_PATH = "configFiles/config.xml";

	/** The helper. */
	private HelperForTests helper = new HelperForTests();
	
	/** The error content. */
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public final void setUpStreams() {
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public final void cleanUpStreams() {
	    System.setErr(null);
	}

	@Test
	public final void loadConfigurationFromPropertiesFileFirst() throws Throwable {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = DEBUG\n"
							+ "messageFormat = Properties %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(PROPERTIES_FILE_PATH, textFile);
		ConfigurationLoader config = new ConfigurationLoader();
		GenericLogger logger = config.getLogger();
		LevelManager levelManager = new LevelManager();
		logger.log(new Level("INFO", levelManager.getLevelValue("INFO")), MESSAGE, null);
		String text = "Properties - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text, file));
	}

	@Test
	public final void loadConfigurationFromXMLFileSecond() throws Throwable {
		File logFile = new File(LOG_PATH);
		logFile.delete();
		File file = new File(PROPERTIES_FILE_PATH);
		file.delete();
		String textFile =	"<configuration>"
							+ "<level>DEBUG</level>"
							+ "<message><format>XML %n %p %n %m</format>"
							+ "<separator>-</separator></message>"
							+ "<outputs><file>log.txt</file>"
							+ "<console>false</console></outputs>"
							+ "</configuration>";
		this.helper.writeNewFileWithText(XML_FILE_PATH, textFile);
		ConfigurationLoader config = new ConfigurationLoader();
		GenericLogger logger = config.getLogger();
		LevelManager levelManager = new LevelManager();
		logger.log(new Level("INFO", levelManager.getLevelValue("INFO")), MESSAGE, null);
		String text = "XML - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text, logFile));
	}

	@Test
	public final void loadConfigurationFromDefaultThird() throws Throwable {
		File logFile = new File(LOG_PATH);
		logFile.delete();
		File propFile = new File(PROPERTIES_FILE_PATH);
		propFile.delete();
		File xmlFile = new File(XML_FILE_PATH);
		xmlFile.delete();
		ConfigurationLoader config = new ConfigurationLoader();
		GenericLogger logger = config.getLogger();
		LevelManager levelManager = new LevelManager();
		logger.log(new Level("INFO", levelManager.getLevelValue("INFO")), MESSAGE, null);
		assertFalse(logFile.exists());
	}
	
	@Test
	public final void loadConfigurationFromNonPropertiesOrXmlFileLogError() throws Throwable {
		File logFile = new File(LOG_PATH);
		logFile.delete();
		String textFile =	"level = DEBUG\n"
							+ "messageFormat = Properties %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText("configFiles/NonExtensionFile", textFile);
		new ConfigurationLoader("configFiles/NonExtensionFile");
		ReaderException e = new ReaderException();
		String text = "There was a ReaderException when reading the configuration file: " + e.getMessage() + "\n Check your configuration file"; 
		assertTrue(errContent.toString().contains(text));
	}

	@Test
	public final void loadConfigurationFromSpecifiedPropertiesFile() throws Throwable {
		File logFile = new File(LOG_PATH);
		logFile.delete();
		String textFile =	"level = DEBUG\n"
							+ "messageFormat = Properties %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(PROPERTIES_FILE_PATH, textFile);
		ConfigurationLoader config = new ConfigurationLoader(PROPERTIES_FILE_PATH);
		GenericLogger logger = config.getLogger();
		LevelManager levelManager = new LevelManager();
		logger.log(new Level("INFO", levelManager.getLevelValue("INFO")), MESSAGE, null);
		String text = "Properties - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text, logFile));
	}

	@Test
	public final void loadConfigurationFromSpecifiedXMLFile() throws Throwable {
		File logFile = new File(LOG_PATH);
		logFile.delete();
		String textFile =	"<configuration>"
							+ "<level>DEBUG</level>"
							+ "<message><format>XML %n %p %n %m</format>"
							+ "<separator>-</separator></message>"
							+ "<outputs><file>log.txt</file>"
							+ "<console>false</console></outputs>"
							+ "</configuration>";
		this.helper.writeNewFileWithText(XML_FILE_PATH, textFile);
		ConfigurationLoader config = new ConfigurationLoader(XML_FILE_PATH);
		GenericLogger logger = config.getLogger();
		LevelManager levelManager = new LevelManager();
		logger.log(new Level("INFO", levelManager.getLevelValue("INFO")), MESSAGE, null);
		String text = "XML - INFO - " + MESSAGE;
		assertTrue(helper.stringInFile(text, logFile));
	}
	
	@Test
	public final void loadNonExistingDirectoryForConfigurationOutputLogError() throws Throwable {
		File file = new File(LOG_PATH);
		file.delete();
		String textFile =	"level = DEBUG\n"
							+ "messageFormat = Properties %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "logToFiles = /dontExist/log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(PROPERTIES_FILE_PATH, textFile);
		new ConfigurationLoader();
		String text = "There was an IOException when initializing outputs: No such file or directory" + "\n Check your configuration file"; 
		assertTrue(errContent.toString().contains(text));
	}

	@Test
	public final void loadCustomOutput() throws Throwable {
		File logFile = new File(LOG_PATH);
		logFile.delete();
		String textFile =	"level = INFO\n"
							+ "messageFormat = Properties %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "customOutputs = logger.outputs.FileOutput,log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(PROPERTIES_FILE_PATH, textFile);
		ConfigurationLoader config = new ConfigurationLoader();
		GenericLogger logger = config.getLogger();
		LevelManager levelManager = new LevelManager();
		logger.log(new Level("INFO", levelManager.getLevelValue("INFO")), MESSAGE, null);
		assertTrue(logFile.exists());
	}
	
	@Test
	public final void loadNonExistingCustomOutputLogError() throws CustomFilterException, OutputException, IOException {
		File logFile = new File(LOG_PATH);
		logFile.delete();
		String textFile =	"level = INFO\n"
							+ "messageFormat = Properties %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "customOutputs = logger.outputs.ComplexOutput\n"
							+ "logToConsole = true";
		this.helper.writeNewFileWithText(PROPERTIES_FILE_PATH, textFile);
		new ConfigurationLoader();
		CustomOutputException e = new CustomOutputException();
		String text = "There was a CustomOutputException when initializing outputs: " + e.getMessage() + "\n Check your configuration file"; 
		assertTrue(errContent.toString().contains(text));
	}

	@Test
	public final void loadCustomFilter() throws Throwable {
		File logFile = new File(LOG_PATH);
		logFile.delete();
		String textFile =	"level = INFO\n"
							+ "messageFormat = Properties %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "customFilter = logger.filters.RegexFilter,.*INFO.*\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(PROPERTIES_FILE_PATH, textFile);
		ConfigurationLoader config = new ConfigurationLoader();
		GenericLogger logger = config.getLogger();
		LevelManager levelManager = new LevelManager();
		logger.log(new Level("INFO", levelManager.getLevelValue("INFO")), MESSAGE, null);
		logger.log(new Level("TRACE", levelManager.getLevelValue("TRACE")), MESSAGE, null);
		String text1 = "Properties" + " - INFO - " + MESSAGE;
		String text2 = "Properties" + " - TRACE - " + MESSAGE;
		assertTrue(helper.stringInFile(text1, logFile));
		assertFalse(helper.stringInFile(text2, logFile));
	}
	
	@Test
	public final void loadNonExistingCustomFilterLogError() throws CustomFilterException, OutputException, IOException {
		File logFile = new File(LOG_PATH);
		logFile.delete();
		String textFile =	"level = INFO\n"
							+ "messageFormat = Properties %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "customFilter = logger.filters.ComplexFilter,.*INFO.*\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = true";
		this.helper.writeNewFileWithText(PROPERTIES_FILE_PATH, textFile);
		new ConfigurationLoader();
		CustomFilterException e = new CustomFilterException();
		String text = "There was a CustomFilterException when initializing filter: " + e.getMessage() + "\n Check your configuration file"; 
		assertTrue(errContent.toString().contains(text));
	}

	@Test
	public final void loadRegExFilter() throws Throwable {
		File logFile = new File(LOG_PATH);
		logFile.delete();
		String textFile =	"level = INFO\n"
							+ "messageFormat = Properties %n %p %n %m\n"
							+ "messageSeparator = -\n"
							+ "regExFilter = .*INFO.*\n"
							+ "logToFiles = log.txt\n"
							+ "logToConsole = false";
		this.helper.writeNewFileWithText(PROPERTIES_FILE_PATH, textFile);
		ConfigurationLoader config = new ConfigurationLoader();
		GenericLogger logger = config.getLogger();
		LevelManager levelManager = new LevelManager();
		logger.log(new Level("INFO", levelManager.getLevelValue("INFO")), MESSAGE, null);
		logger.log(new Level("TRACE", levelManager.getLevelValue("TRACE")), MESSAGE, null);
		String text1 = "Properties" + " - INFO - " + MESSAGE;
		String text2 = "Properties" + " - TRACE - " + MESSAGE;
		assertTrue(helper.stringInFile(text1, logFile));
		assertFalse(helper.stringInFile(text2, logFile));
	}

}
