package main.java.logger;

import java.io.IOException;
import java.util.ArrayList;


public abstract class Logger {

	private Formatter formatter;
	private ArrayList<Writable> outputs;
	private boolean consoleActive;
	private Level configLevel;
	private static final String TAG_DEBUG = "DEBUG";
	private static final String TAG_INFO = "INFO";
	private static final String TAG_WARN = "WARN";
	private static final String TAG_ERROR = "ERROR";
	private static final String TAG_FATAL = "FATAL";
	private static final String TAG_OFF = "OFF";
	private static final int DEBUG_VALUE = 5;
	private static final int INFO_VALUE = 4;
	private static final int WARN_VALUE = 3;
	private static final int ERROR_VALUE = 2;
	private static final int FATAL_VALUE = 1;
	private static final int OFF_VALUE = 0;

	
	public Logger(Formatter formatter) {
		super();
		this.formatter = formatter;
		this.outputs = new ArrayList<Writable>();
		this.consoleActive = false;
		this.configLevel = new Level(TAG_OFF, OFF_VALUE);
	}
	
	public void addConsoleOutput() {
		if (!consoleActive) {
			outputs.add(new ConsoleOutput());
			consoleActive = true;
		}
	}
	
	public void addFileOutput(String filePath) throws IOException {
		outputs.add(new FileOutput(filePath));
	}
	
	public void debug(String logMsg) throws WriteException {
		Level level = new Level(TAG_DEBUG, DEBUG_VALUE);
		if (shouldLog(level)) {
			log(level.getName(), logMsg);
		}
	}

	public void info(String logMsg) throws WriteException {
		Level level = new Level(TAG_INFO, INFO_VALUE);
		if (shouldLog(level)) {
			log(level.getName(), logMsg);
		}
	}

	public void warn(String logMsg) throws WriteException {
		Level level = new Level(TAG_WARN, WARN_VALUE);
		if (shouldLog(level)) {
			log(level.getName(), logMsg);
		}
	}

	public void error(String logMsg) throws WriteException {
		Level level = new Level(TAG_ERROR, ERROR_VALUE);
		if (shouldLog(level)) {
			log(level.getName(), logMsg);
		}
	}

	public void fatal(String logMsg) throws WriteException {
		Level level = new Level(TAG_FATAL, FATAL_VALUE);
		if (shouldLog(level)) {
			log(level.getName(), logMsg);
		}
	}
	
	private Boolean shouldLog(Level level) {
		if (level.getValue() <= this.configLevel.getValue()) {
			return true;			
		}
		return false;
	}
	
	private void log(String level, String logMsg) throws WriteException {
		// El formatter deberia recibir el level y ya formar el mensaje final
		String formatedLog = formatter.giveFormat(level, logMsg);
		// Esta logica deberia ir adentro del formatter
		String finalMsg = "[" + level + "]:" + formatedLog;
		for (Writable output: outputs) {
			output.write(finalMsg);
		}
	}

}
