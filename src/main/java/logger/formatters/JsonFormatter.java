package logger.formatters;

import logger.Level;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class JsonFormatter give json format to messages.
 */
public class JsonFormatter implements Formatter {
	
	/** The logger name. */
	private String loggerName; 
	
	/**
	 * Instantiates a new json formatter.
	 *
	 * @param loggername the logger name
	 */
	public JsonFormatter(String loggername) {
		this.loggerName = loggername;
	}

	@Override
	public final String giveFormat(final Level level, final String logMsg) {
		JSONObject json = new JSONObject();
		json.element("datetime", getDate());
		json.element("level", level.getName());
		json.element("logger", loggerName);
		json.element("message", logMsg);
		return json.toString();
	}
	
	/**
	 * Gets the actual date.
	 *
	 * @return the date
	 */
	private String getDate() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		return dateFormat.format(date);
	}

}
