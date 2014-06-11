package logger.configurationReaders;

/**
 * The Interface ConfigurationReader thought to read configuration files in different formats.
 */
public interface ConfigurationReader {

	/**
	 * Read configuration.
	 *
	 * @return Configuration object with the configuration read from file
	 * @throws ReaderException when an error occurs in the reading
	 */
	Configuration readConfiguration() throws ReaderException;

}
