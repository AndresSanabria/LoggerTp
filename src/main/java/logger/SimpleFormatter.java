package main.java.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleFormatter extends Formatter {

	public SimpleFormatter(String format) {
		super(format);
	}
	
	public SimpleFormatter(String format, String separator) {
		super(format, separator);
	}

	@Override
	public String giveFormat(String level, String logMsg) {
		String formattedLog = new String(this.format);
		formattedLog = formattedLog.replaceAll("%n", separator);
		formattedLog = formattedLog.replaceAll("%p", level);
		formattedLog = formattedLog.replaceAll("%m", logMsg);

		formattedLog = formattedLog.replaceAll("%t", Thread.currentThread().getName());
		formattedLog = formattedLog.replaceAll("%L", String.valueOf((Thread.currentThread().getStackTrace()[4].getLineNumber())));
		formattedLog = formattedLog.replaceAll("%F", String.valueOf((Thread.currentThread().getStackTrace()[4].getFileName())));
		formattedLog = formattedLog.replaceAll("%M", String.valueOf((Thread.currentThread().getStackTrace()[4].getMethodName())));
		
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
