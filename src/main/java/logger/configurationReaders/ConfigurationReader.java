package logger.configurationReaders;

import java.io.IOException;

/**
 * The Interface ConfigurationReader thought to read configuration files in different formats.
 */
public interface ConfigurationReader {

	/**
	 * Read configuration.
	 *
	 * @return Configuration object with the configuration read from file
	 * @throws IOException when an error occurs in an I/O operation
	 */
	Configuration readConfiguration() throws IOException;

}
