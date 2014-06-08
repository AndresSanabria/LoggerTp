package logger.configurationReaders;

import java.util.List;

/**
 * The Class Configuration stores the configuration of the logger.
 */
public class Configuration {

	/** The level of the Logger. */
	private String level;

	/** The format in which the log will be written. */
	private String messageFormat;

	/** The message separator. */
	private String messageSeparator;

	/** The files where to log. */
	private String[] logToFiles;

	/** The custom outputs where to log. */
	private List<String[]> customOutputs;

	/** Should log to Console?. */
	private Boolean logToConsole;

	/** The regEx filter. */
	private String regExFilter;

	/** The custom filter. */
	private String[] customFilter;


	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public final String getLevel() {
		return this.level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the level
	 */
	public final void setLevel(final String level) {
		this.level = level;
	}

	/**
	 * Gets the message format.
	 *
	 * @return the message format
	 */
	public final String getMessageFormat() {
		return this.messageFormat;
	}

	/**
	 * Sets the message format.
	 *
	 * @param messageFormat the message format
	 */
	public final void setMessageFormat(final String messageFormat) {
		this.messageFormat = messageFormat;
	}

	/**
	 * Gets the message separator.
	 *
	 * @return the message separator
	 */
	public final String getMessageSeparator() {
		return this.messageSeparator;
	}

	/**
	 * Sets the message separator.
	 *
	 * @param messageSeparator the message separator
	 */
	public final void setMessageSeparator(final String messageSeparator) {
		this.messageSeparator = messageSeparator;
	}

	/**
	 * Gets files where to log.
	 *
	 * @return the files to log
	 */
	public final String[] getLogToFiles() {
		return this.logToFiles;
	}

	/**
	 * Sets files where to log.
	 *
	 * @param logToFiles the files to log
	 */
	public final void setLogToFiles(final String[] logToFiles) {
		this.logToFiles = logToFiles;
	}

	/**
	 * Gets custom outputs where to log.
	 *
	 * @return the custom outputs to log
	 */
	public final List<String[]> getCustomOutputs() {
		return this.customOutputs;
	}

	/**
	 * Sets custom outputs where to log.
	 *
	 * @param customOutputs the custom outputs to log
	 */
	public final void setCustomOutputs(final List<String[]> customOutputs) {
		this.customOutputs = customOutputs;
	}

	/**
	 * Gets if log to console.
	 *
	 * @return if should log to console
	 */
	public final Boolean getLogToConsole() {
		return this.logToConsole;
	}

	/**
	 * Sets if log to console.
	 *
	 * @param logToConsole if should log to console
	 */
	public final void setLogToConsole(final Boolean logToConsole) {
		this.logToConsole = logToConsole;
	}

	/**
	 * Gets the regEx filter.
	 *
	 * @return the regEx filter
	 */
	public final String getRegExFilter() {
		return this.regExFilter;
	}

	/**
	 * Sets the regEx filter.
	 *
	 * @param regExFilter the regEx filter
	 */
	public final void setRegExFilter(final String regExFilter) {
		this.regExFilter = regExFilter;
	}

	/**
	 * Gets the custom filter.
	 *
	 * @return the custom filter
	 */
	public final String[] getCustomFilter() {
		return this.customFilter;
	}

	/**
	 * Sets the custom filter.
	 *
	 * @param customFilter the custom filter to set
	 */
	public final void setCustomFilter(final String[] customFilter) {
		this.customFilter = customFilter;
	}

}
