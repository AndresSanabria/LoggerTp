package logger.configurationReaders;

import java.io.FileNotFoundException;

/**
 * The Interface ConfigurationReader thought to read configuration files in different formats.
 */
public interface ConfigurationReader {

	/**
	 * Read configuration.
	 *
	 * @param configFilePath the configuration file path
	 * @return Configuration object with the configuration read from file
	 * @throws FileNotFoundException 
	 */
	public Configuration readConfiguration(String configFilePath) throws FileNotFoundException;

}