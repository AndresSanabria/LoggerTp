package main.java.logger.configurationReaders;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * The Class XMLFileReader reads the configuration from an .xml file.
 */
public class XMLFileReader implements ConfigurationReader {

	/** The Constant FILE_SEPARATOR. */
	private static final String FILE_SEPARATOR = ";";

	/** The file path. */
	private String filePath;
	
	
	/**
	 * Instantiates a new XMLFileReader.
	 * 
	 * @param filePath the path to the file to be read
	 */
	public XMLFileReader(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public Configuration readConfiguration() throws IOException {
		try {
			Configuration config = new Configuration();
			File configFile = new File(this.filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(configFile);
			doc.getDocumentElement().normalize();
			
			config.setLevel(this.getLevelNodeValue(doc));
			config.setMessageFormat(this.getValueFromNodeInElement(doc, "message", "format"));
			config.setMessageSeparator(this.getValueFromNodeInElement(doc, "message", "separator"));
			config.setLogToFiles(this.getValueFromNodeInElement(doc, "outputs", "files").split(FILE_SEPARATOR));
			config.setLogToConsole(this.getConsoleNodeValue(doc));
			return config;
		} catch (ParserConfigurationException e) {
			System.err.println("There was a ParserConfigurationException when parsing the configuration: "+ e.getMessage());
			System.err.println("Check your configuration file");
		} catch (SAXException e) {
			System.err.println("There was a SAXException when loading the configuration: "+ e.getMessage());
			System.err.println("Check your configuration file");
		}
		return null;
	}
	
	/**
	 * Gets the node value.
	 *
	 * @param tag the tag to be read
	 * @param element the element where the tag is
	 * @return the node value
	 */
	private String getNodeValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag);
		if (nodes.item(0) == null) {
			return "";
		}
		Node node = nodes.item(0).getChildNodes().item(0);
		if (node == null) {
			return "";
		}
		return node.getNodeValue();
	}
	
	/**
	 * Gets the value from a node in an element.
	 *
	 * @param doc the doc parsed
	 * @param elementTag the tag of the element
	 * @param tag the tag in the element
	 * @return the value from a node in an element
	 */
	private String getValueFromNodeInElement(Document doc, String elementTag, String tag) {
		NodeList nodes = doc.getElementsByTagName(elementTag);
		Node node = nodes.item(0);
		return this.getNodeValue(tag, (Element) node);
	}
	
	/**
	 * Gets the element node value.
	 *
	 * @param doc the doc parsed
	 * @param elementTag the tag of the element
	 * @return the element node value
	 */
	private String getElementNodeValue(Document doc, String elementTag) {
		NodeList nodes = doc.getElementsByTagName(elementTag);
		Node node = nodes.item(0);
		if (node == null) {
			return "";
		}
		node = node.getChildNodes().item(0);
		if (node == null) {
			return "";
		}
		return node.getNodeValue();
	}

	/**
	 * Gets the level node value.
	 *
	 * @param doc the doc parsed
	 * @return the level node value
	 */
	private String getLevelNodeValue(Document doc) {
		String value = this.getElementNodeValue(doc, "level");
		if (value.isEmpty()) {
			value = "OFF";
		}
		return value;
	}
	
	/**
	 * Gets the console node value.
	 *
	 * @param doc the doc parsed
	 * @return the console node value
	 */
	private Boolean getConsoleNodeValue(Document doc) {
		String value = this.getValueFromNodeInElement(doc, "outputs", "console");
		if (value.isEmpty()) {
			return false;
		}
		return Boolean.valueOf(value);
	}

}
