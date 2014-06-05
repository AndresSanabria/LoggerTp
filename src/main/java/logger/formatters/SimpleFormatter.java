package logger.formatters;

import java.text.SimpleDateFormat;
import java.util.Date;

import logger.Level;

/**
 * The Class SimpleFormatter give format to messages replacing pseudo-variables 
 * in the format pattern with corresponding content.
 */
public class SimpleFormatter implements Formatter {
	
	/** The logger name. */
	private String loggerName = "";
	
	/** The format pattern. */
	private String format;
	
	/** The separator field. */
	private String separator = "-" ;
	
	/** The caller stack distance. */
	private Integer callerStackDistance;

	/**
	 * Instantiates a new simple formatter.
	 *
	 * @param format the format pattern
	 * @param callerStackDistance the caller stack distance
	 */
	public SimpleFormatter(String format, Integer callerStackDistance) {
		this.callerStackDistance = callerStackDistance;
		this.format = format;
	}
	
	/**
	 * Instantiates a new simple formatter.
	 *
	 * @param format the format pattern
	 * @param callerStackDistance the caller stack distance
	 * @param separator the separator to use
	 */
	public SimpleFormatter(String format, Integer callerStackDistance, String separator) {
		this.callerStackDistance = callerStackDistance;
		this.format = format;
		this.separator = separator;
	}
	
	/**
	 * Instantiates a new simple formatter.
	 *
	 * @param format the format pattern
	 * @param callerStackDistance the caller stack distance
	 * @param separator the separator to use
	 * @param loggerName the logger name
	 */
	public SimpleFormatter(String format, Integer callerStackDistance, String separator, String loggerName) {
		this.callerStackDistance = callerStackDistance;
		this.format = format;
		this.separator = separator;
		this.loggerName = loggerName;
	}
	
	/**
	 * Instantiates a new simple formatter.
	 *
	 * @param format the format pattern
	 * @param callerStackDistance the caller stack distance
	 * @param loggerName the logger name
	 */
	public SimpleFormatter(String format, String loggerName, Integer callerStackDistance) {
		this.callerStackDistance = callerStackDistance;
		this.format = format;
		this.loggerName = loggerName;
	}

	public String giveFormat(Level level, String logMsg) {
		String formattedLog = new String(this.format);
		formattedLog = formattedLog.replaceAll("%n", separator);
		formattedLog = formattedLog.replaceAll("%p", level.getName());
		formattedLog = formattedLog.replaceAll("%m", logMsg);
		formattedLog = formattedLog.replaceAll("%g", loggerName);
		formattedLog = formattedLog.replaceAll("%t", Thread.currentThread().getName());
		formattedLog = formattedLog.replaceAll("%L", String.valueOf((Thread.currentThread().getStackTrace()[this.callerStackDistance].getLineNumber())));
		formattedLog = formattedLog.replaceAll("%F", String.valueOf((Thread.currentThread().getStackTrace()[this.callerStackDistance].getFileName())));
		formattedLog = formattedLog.replaceAll("%M", String.valueOf((Thread.currentThread().getStackTrace()[this.callerStackDistance].getMethodName())));
		
		formattedLog = replaceDate(formattedLog);
		formattedLog = formattedLog.replaceAll("%%", "%");
		return formattedLog;
	}

	/**
	 * Replace date pseudo-variables in log message with actual data.
	 *
	 * @param log the log message that is being formatted
	 * @return the log message with dates already formatted
	 */
	private String replaceDate(String log) {
		String formattedLog = new String(log);
		String regex = "(.*)(%d\\{)([^\\}]*)(\\})(.*)";
		Date date = new Date();
		while(formattedLog.matches(regex)){
			String pattern = formattedLog.replaceFirst(regex, "$3");
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			String date_string = format.format(date);
			String patron = "%d\\{" + pattern + "\\}";
			formattedLog = formattedLog.replaceAll(patron, date_string);
		}
		return formattedLog;
	}

}
