package logger.configurationReaders;

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
import java.util.ArrayList;
import java.util.List;

/**
 * The Class XMLFileReader reads the configuration from an .xml file.
 */
public class XMLFileReader implements ConfigurationReader {

	/** The file path. */
	private String filePath;

	/** The Constant DEFAULT_CONSOLE. */
	private static final Boolean DEFAULT_CONSOLE = false;
	
	/** The Constant DEFAULT_LEVEL. */
	private static final String DEFAULT_LEVEL = "OFF";

	/** The Constant DEFAULT_MESSAGE_FORMAT_LEVEL. */
	private static final String DEFAULT_MESSAGE_FORMAT = "%p %n %t %n %m";
	
	/** The Constant LEVEL_TAG. */
	private static final String LEVEL_TAG = "level";
	
	/** The Constant MESSAGE_TAG. */
	private static final String MESSAGE_TAG = "message";
	
	/** The Constant MESSAGE_FORMAT_TAG. */
	private static final String MESSAGE_FORMAT_TAG = "format";

	/** The Constant MESSAGE_SEPARATOR_TAG. */
	private static final String MESSAGE_SEPARATOR_TAG = "separator";

	/** The Constant OUTPUTS_TAG. */
	private static final String OUTPUTS_TAG = "outputs";

	/** The Constant CUSTOM_TAG. */
	private static final String CUSTOM_TAG = "custom";

	/** The Constant IMPLEMENTOR_TAG. */
	private static final String IMPLEMENTOR_TAG = "implementor";

	/** The Constant PARAM_TAG. */
	private static final String PARAM_TAG = "param";
	
	/** The Constant FILE_TAG. */
	private static final String FILE_TAG = "file";

	/** The Constant CONSOLE_TAG. */
	private static final String CONSOLE_TAG = "console";
	
	/** The Constant FILTER_TAG. */
	private static final String FILTER_TAG = "filter";

	/** The Constant REGEX_FILTER_TAG. */
	private static final String REGEX_FILTER_TAG = "regEx";
	
	
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
			config.setMessageFormat(this.getMessageFormatNodeValue(doc));
			config.setMessageSeparator(this.getMessageSeparatorNodeValue(doc));
			config.setLogToFiles(this.getFileNodesValue(doc));
			config.setCustomOutputs(this.getCustomOutputNodesValue(doc));
			config.setLogToConsole(this.getConsoleNodeValue(doc));
			config.setRegExFilter(this.getRegExFilterNodeValue(doc));
			config.setCustomFilter(this.getCustomFilterNodeValue(doc));
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
	 * Gets the level node value.
	 *
	 * @param doc the doc parsed
	 * @return the level node value
	 */
	private String getLevelNodeValue(Document doc) {
		Node node = this.getFirstLevelNodeByTag(doc, LEVEL_TAG);
		if (node == null) {
			return DEFAULT_LEVEL;
		}
		String value = this.getNodeValue(node);
		if (value.isEmpty()) {
			value = DEFAULT_LEVEL;
		}
		return value;
	}
	
	/**
	 * Gets the message format node value.
	 *
	 * @param doc the doc parsed
	 * @return the message format node value
	 */
	private String getMessageFormatNodeValue(Document doc) {
		Node messageNode = this.getFirstLevelNodeByTag(doc, MESSAGE_TAG);
		if (messageNode == null) {
			return DEFAULT_MESSAGE_FORMAT;
		}
		Node formatNode = this.getNodeByTagInNode(messageNode, MESSAGE_FORMAT_TAG);
		if (formatNode == null) {
			return DEFAULT_MESSAGE_FORMAT;
		}
		String value = this.getNodeValue(formatNode);
		if (value.isEmpty()) {
			value = DEFAULT_MESSAGE_FORMAT;
		}
		return value;
	}
	
	/**
	 * Gets the message separator node value.
	 *
	 * @param doc the doc parsed
	 * @return the message separator node value
	 */
	private String getMessageSeparatorNodeValue(Document doc) {
		Node messageNode = this.getFirstLevelNodeByTag(doc, MESSAGE_TAG);
		if (messageNode == null) {
			return null;
		}
		Node separatorNode = this.getNodeByTagInNode(messageNode, MESSAGE_SEPARATOR_TAG);
		if (separatorNode == null) {
			return null;
		}
		String value = this.getNodeValue(separatorNode);
		if (value.isEmpty()) {
			value = null;
		}
		return value;
	}

	/**
	 * Gets the file nodes value.
	 *
	 * @param doc the doc parsed
	 * @return the file nodes value
	 */
	private String[] getFileNodesValue(Document doc) {
		List<String> values = new ArrayList<>();
		Node outputsNode = this.getFirstLevelNodeByTag(doc, OUTPUTS_TAG);
		if (outputsNode == null) {
			return null;
		}
		NodeList fileNodes = this.getNodeListByTagInNode(outputsNode, FILE_TAG);
		for (int i = 0; i < fileNodes.getLength(); i++) {
			String value = this.getNodeValue(fileNodes.item(i));
			if (!value.isEmpty()) {
				values.add(value);
			}
		}
		if (values.size() == 0) {
			return null;
		}
		return values.toArray(new String[values.size()]);
	}
	
	/**
	 * Gets the custom output nodes value.
	 *
	 * @param doc the doc parsed
	 * @return the custom output nodes value
	 */
	private List<String[]> getCustomOutputNodesValue(Document doc) {
		List<String[]> customOutputs = new ArrayList<>();
		Node outputsNode = this.getFirstLevelNodeByTag(doc, OUTPUTS_TAG);
		if (outputsNode == null) {
			return null;
		}
		NodeList customNodes = this.getNodeListByTagInNode(outputsNode, CUSTOM_TAG);
		for (int i = 0; i < customNodes.getLength(); i++) {
			List<String> values = new ArrayList<>();
			
			Node implementorNode = this.getNodeByTagInNode(customNodes.item(i), IMPLEMENTOR_TAG);
			if (implementorNode == null) {
				continue;
			}
			String implementorValue = this.getNodeValue(implementorNode);
			if (implementorValue.isEmpty()) {
				continue;
			}
			values.add(implementorValue);
			
			NodeList paramNodes = this.getNodeListByTagInNode(customNodes.item(i), PARAM_TAG);
			for (int j = 0; j < paramNodes.getLength(); j++) {
				String paramValue = this.getNodeValue(paramNodes.item(j));
				values.add(paramValue);
			}
			
			customOutputs.add(values.toArray(new String[values.size()]));
		}
		if (customOutputs.size() == 0) {
			return null;
		}
		return customOutputs;
	}
	
	/**
	 * Gets the console node value.
	 *
	 * @param doc the doc parsed
	 * @return the console node value
	 */
	private Boolean getConsoleNodeValue(Document doc) {
		Node outputsNode = this.getFirstLevelNodeByTag(doc, OUTPUTS_TAG);
		if (outputsNode == null) {
			return DEFAULT_CONSOLE;
		}
		Node consoleNode = this.getNodeByTagInNode(outputsNode, CONSOLE_TAG);
		if (consoleNode == null) {
			return DEFAULT_CONSOLE;
		}
		String value = this.getNodeValue(consoleNode);
		if (value.isEmpty()) {
			return DEFAULT_CONSOLE;
		}
		return Boolean.valueOf(value);
	}
	
	/**
	 * Gets the regEx filter node value.
	 *
	 * @param doc the doc parsed
	 * @return the regEx filter node value
	 */
	private String getRegExFilterNodeValue(Document doc) {
		Node filterNode = this.getFirstLevelNodeByTag(doc, FILTER_TAG);
		if (filterNode == null) {
			return null;
		}
		Node regExFilterNode = this.getNodeByTagInNode(filterNode, REGEX_FILTER_TAG);
		if (regExFilterNode == null) {
			return null;
		}
		String value = this.getNodeValue(regExFilterNode);
		if (value.isEmpty()) {
			value = null;
		}
		return value;
	}

	/**
	 * Gets the custom filter node value.
	 *
	 * @param doc the doc parsed
	 * @return the custom filter node value
	 */
	private String[] getCustomFilterNodeValue(Document doc) {
		List<String> values = new ArrayList<>();
		Node filterNode = this.getFirstLevelNodeByTag(doc, FILTER_TAG);
		if (filterNode == null) {
			return null;
		}
		Node customFilterNode = this.getNodeByTagInNode(filterNode, CUSTOM_TAG);
		if (customFilterNode == null) {
			return null;
		}
		
		Node implementorNode = this.getNodeByTagInNode(customFilterNode, IMPLEMENTOR_TAG);
		if (implementorNode == null) {
			return null;
		}
		String implementorValue = this.getNodeValue(implementorNode);
		if (implementorValue.isEmpty()) {
			return null;
		}
		values.add(implementorValue);
		
		NodeList paramNodes = this.getNodeListByTagInNode(customFilterNode, PARAM_TAG);
		for (int j = 0; j < paramNodes.getLength(); j++) {
			String paramValue = this.getNodeValue(paramNodes.item(j));
			values.add(paramValue);
		}
		
		return values.toArray(new String[values.size()]);
	}

	/**
	 * Gets the first level node by tag in the document.
	 *
	 * @param doc the doc parsed
	 * @param tag the tag of the node
	 * @return the first level node
	 */
	private Node getFirstLevelNodeByTag(Document doc, String tag) {
		NodeList nodes = doc.getElementsByTagName(tag);
		Node node = nodes.item(0);
		return node;
	}
	
	/**
	 * Gets the node list by tag in the upper node.
	 *
	 * @param node the upper node
	 * @param tag the tag of the node list
	 * @return the node list
	 */
	private NodeList getNodeListByTagInNode(Node node, String tag) {
		Element element = (Element) node;
		NodeList nodes = element.getElementsByTagName(tag);
		return nodes;
	}
	
	/**
	 * Gets the node by tag in the upper node.
	 *
	 * @param node the upper node
	 * @param tag the tag of the node
	 * @return the node
	 */
	private Node getNodeByTagInNode(Node upperNode, String tag) {
		Element element = (Element) upperNode;
		Node node = element.getElementsByTagName(tag).item(0);
		return node;
	}
	
	/**
	 * Gets the value of the node.
	 *
	 * @param node
	 * @return the value of the node
	 */
	private String getNodeValue(Node node) {
		Node nodeValue = node.getChildNodes().item(0);
		if (nodeValue == null) {
			return "";
		}
		return nodeValue.getNodeValue();
	}
	
}
