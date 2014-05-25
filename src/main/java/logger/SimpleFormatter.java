package main.java.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleFormatter implements Formatter {
	
	private String format;
	private String separator = "-" ;
	private Integer callerStackDistance;//4

	public SimpleFormatter(String format, Integer callerStackDistance) {
		this.callerStackDistance = callerStackDistance;
		this.format = format;
	}
	
	public SimpleFormatter(String format, Integer callerStackDistance, String separator) {
		this.callerStackDistance = callerStackDistance;
		this.format = format;
		this.separator = separator;
	}

	public String giveFormat(Level level, String logMsg) {
		String formattedLog = new String(this.format);
		formattedLog = formattedLog.replaceAll("%n", separator);
		formattedLog = formattedLog.replaceAll("%p", level.getName());
		formattedLog = formattedLog.replaceAll("%m", logMsg);

		formattedLog = formattedLog.replaceAll("%t", Thread.currentThread().getName());
		formattedLog = formattedLog.replaceAll("%L", String.valueOf((Thread.currentThread().getStackTrace()[this.callerStackDistance].getLineNumber())));
		formattedLog = formattedLog.replaceAll("%F", String.valueOf((Thread.currentThread().getStackTrace()[this.callerStackDistance].getFileName())));
		formattedLog = formattedLog.replaceAll("%M", String.valueOf((Thread.currentThread().getStackTrace()[this.callerStackDistance].getMethodName())));
		
		formattedLog = replaceDate(formattedLog);
		formattedLog = formattedLog.replaceAll("%%", "%");
		return formattedLog;
	}

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
