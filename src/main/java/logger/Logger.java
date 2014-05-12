package main.java.logger;

public abstract class Logger {

	private Formatter formatter;

	public Logger(Formatter formatter) {
		super();
		this.formatter = formatter;
	}

	public void debug(String logMsg) {
		if (shouldDebug()) {
			log("DEBUG", logMsg);
		}
	}

	public void info(String logMsg) {
		if (shouldInfo()) {
			log("INFO", logMsg);
		}
	}

	public void warn(String logMsg) {
		if (shouldWarn()) {
			log("WARN", logMsg);
		}
	}

	public void error(String logMsg) {
		if (shouldError()) {
			log("ERROR", logMsg);
		}
	}

	public void fatal(String logMsg) {
		if (shouldFatal()) {
			log("FATAL", logMsg);
		}
	}

	public void debug(String logMsg, String fileName, String methodName,
			Integer lineNumber) {
		if (shouldDebug()) {
			log("DEBUG", logMsg, fileName, methodName, lineNumber);
		}
	}

	public void info(String logMsg, String fileName, String methodName,
			Integer lineNumber) {
		if (shouldInfo()) {
			log("INFO", logMsg, fileName, methodName, lineNumber);
		}
	}

	public void warn(String logMsg, String fileName, String methodName,
			Integer lineNumber) {
		if (shouldWarn()) {
			log("WARN", logMsg, fileName, methodName, lineNumber);
		}
	}

	public void error(String logMsg, String fileName, String methodName,
			Integer lineNumber) {
		if (shouldError()) {
			log("ERROR", logMsg, fileName, methodName, lineNumber);
		}
	}

	public void fatal(String logMsg, String fileName, String methodName,
			Integer lineNumber) {
		if (shouldFatal()) {
			log("FATAL", logMsg, fileName, methodName, lineNumber);
		}
	}

	protected boolean shouldDebug() {
		return true;
	}

	protected boolean shouldInfo() {
		return true;
	}

	protected boolean shouldWarn() {
		return true;
	}

	protected boolean shouldError() {
		return true;
	}

	protected boolean shouldFatal() {
		return true;
	}

	private void log(String level, String logMsg) {
		String formatedLog = formatter.giveFormat(level, logMsg);
		// TODO:Escribir en los outputs el msg
		System.out.println("[" + level + "]:" + formatedLog);
	}

	private void log(String level, String logMsg, String fileName,
			String methodName, Integer lineNumber) {
		String formatedLog = formatter.giveFormat(level, logMsg, fileName,
				methodName, lineNumber);
		// TODO:Escribir en los outputs el msg
		System.out.println("[" + level + "]:" + formatedLog);
	}

}
