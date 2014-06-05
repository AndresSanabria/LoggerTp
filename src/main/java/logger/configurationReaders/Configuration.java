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
	public String getLevel() {
		return this.level;
	}
	
	/**
	 * Sets the level.
	 *
	 * @param the level
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * Gets the message format.
	 *
	 * @return the message format
	 */
	public String getMessageFormat() {
		return this.messageFormat;
	}
	
	/**
	 * Sets the message format.
	 *
	 * @param the message format
	 */
	public void setMessageFormat(String messageFormat) {
		this.messageFormat = messageFormat;
	}

	/**
	 * Gets the message separator.
	 *
	 * @return the message separator
	 */
	public String getMessageSeparator() {
		return this.messageSeparator;
	}
	
	/**
	 * Sets the message separator.
	 *
	 * @param the message separator
	 */
	public void setMessageSeparator(String messageSeparator) {
		this.messageSeparator = messageSeparator;
	}

	/**
	 * Gets files where to log.
	 *
	 * @return the files to log
	 */
	public String[] getLogToFiles() {
		return this.logToFiles;
	}
	
	/**
	 * Sets files where to log.
	 *
	 * @param the files to log
	 */
	public void setLogToFiles(String[] logToFiles) {
		this.logToFiles = logToFiles;
	}
	
	/**
	 * Gets custom outputs where to log.
	 *
	 * @return the custom outputs to log
	 */
	public List<String[]> getCustomOutputs() {
		return this.customOutputs;
	}
	
	/**
	 * Sets custom outputs where to log.
	 *
	 * @param the custom outputs to log
	 */
	public void setCustomOutputs(List<String[]> customOutputs) {
		this.customOutputs = customOutputs;
	}

	/**
	 * Gets if log to console.
	 *
	 * @return if should log to console
	 */
	public Boolean getLogToConsole() {
		return this.logToConsole;
	}
	
	/**
	 * Sets if log to console.
	 *
	 * @param if should log to console
	 */
	public void setLogToConsole(Boolean logToConsole) {
		this.logToConsole = logToConsole;
	}
	
	/**
	 * Gets the regEx filter.
	 *
	 * @return the regEx filter
	 */
	public String getRegExFilter() {
		return this.regExFilter;
	}
	
	/**
	 * Sets the regEx filter.
	 *
	 * @param the regEx filter
	 */
	public void setRegExFilter(String regExFilter) {
		this.regExFilter = regExFilter;
	}

	/**
	 * Gets the custom filter.
	 * 
	 * @return the custom filter
	 */
	public String[] getCustomFilter() {
		return this.customFilter;
	}

	/**
	 * Sets the custom filter.
	 * 
	 * @param customFilter the custom filter to set
	 */
	public void setCustomFilter(String[] customFilter) {
		this.customFilter = customFilter;
	}
	
}