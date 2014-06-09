package logger;

import java.io.IOException;
import java.util.List;

import logger.customFactory.CustomFactory;
import logger.customFactory.CustomFilterException;
import logger.customFactory.CustomOutputException;
import logger.filters.RegexFilter;
import logger.formatters.Formatter;
import logger.writables.ConsoleOutput;
import logger.writables.FileOutput;

/**
 * The Class LoggerManager loads the configurations and initializes the logger.
 */
public class LoggerManager {

	/** The logger. */
	private Logger logger;

	/** The configuration loader. */
	private ConfigurationLoader configLoader;


	/**
	 * Instantiates the LoggerManager.
	 *
	 * @param loggerName the name of the logger
	 */
	public LoggerManager(final String loggerName) {
		this.configLoader = new ConfigurationLoader();
		this.configLoader.loadConfiguration();
		this.initializeLogger(loggerName);
	}

	/**
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public final Logger getLogger() {
		return this.logger;
	}

	/**
	 * Initializes the logger.
	 *
	 * @param loggerName the name of the logger
	 */
	private void initializeLogger(final String loggerName) {
		Formatter formatter = this.configLoader.initializeFormatter();
		String levelName = this.configLoader.getConfiguration().getLevel();
		LevelManager levelManager = new LevelManager();
		Level level = new Level(levelName, levelManager.getLevelValue(levelName));
		this.logger = new Logger(loggerName, level, formatter);
		try {
			this.initializeOutputs();
			this.initializeFilter();
		} catch (IOException e) {
			handleException("There was an IOException when Intializing outputs: " + e.getMessage() + "\n Check your Configuration file");
		} catch (CustomOutputException e) {
			handleException("There was an CustomOutputException when Intializing outputs: " + e.getMessage() + "\n Check your Configuration file");
		} catch (CustomFilterException e) {
			handleException("There was an CustomFilterException when Intializing filter: " + e.getMessage() + "\n Check your Configuration file");
		}
	}

	/**
	 * Initializes outputs.
	 *
	 * @throws IOException Signals that an I/O exception has occurred
	 * @throws CustomOutputException when an error occurs in the creation of a CustomOutput
	 */
	private void initializeOutputs() throws IOException, CustomOutputException {
		if (this.configLoader.getConfiguration().getLogToConsole()) {
			this.logger.addOutput(new ConsoleOutput());
		}
		String[] fileOutputs = this.configLoader.getConfiguration().getLogToFiles();
		if (fileOutputs != null) {
			for (String fileOutput: fileOutputs) {
				if (!fileOutput.isEmpty()) {
					this.logger.addOutput(new FileOutput(fileOutput));
				}
			}
		}
		List<String[]> customOutputs = this.configLoader.getConfiguration().getCustomOutputs();
		if (customOutputs != null) {
			CustomFactory outputFactory = new CustomFactory();
			for (String[] customOutput: customOutputs) {
				this.logger.addOutput(outputFactory.createCustomOutput(customOutput[0], java.util.Arrays.copyOfRange(customOutput, 1, customOutput.length)));
			}
		}
	}

	/**
	 * Initializes filter.
	 *
	 * @throws CustomFilterException when an error occurs in the creation of a CustomFilter
	 */
	private void initializeFilter() throws CustomFilterException {
		String regExFilter = this.configLoader.getConfiguration().getRegExFilter();
		if (regExFilter == null) {
			String[] customFilter = this.configLoader.getConfiguration().getCustomFilter();
			if (customFilter == null) {
				this.logger.setFilter(null);
			} else {
				CustomFactory filterFactory = new CustomFactory();
				this.logger.setFilter(filterFactory.createCustomFilter(customFilter[0], java.util.Arrays.copyOfRange(customFilter, 1, customFilter.length)));
			}
		} else {
			this.logger.setFilter(new RegexFilter(regExFilter));
		}
	}

	/**
	 * Handle exception caught.
	 *
	 * @param message the message to be shown
	 */
	private void handleException(final String message) {
		System.err.println(message);
	}

}
