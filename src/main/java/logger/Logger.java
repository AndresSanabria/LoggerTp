package main.java.logger;

//import static org.junit.Assert.assertTrue;

//import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Scanner;


public class Logger {

	private Formatter formatter;
	private ArrayList<Writable> outputs;
	private boolean consoleActive;
	private Level configLevel;
	private Level currentLevel;
	private enum levelValues {
		OFF, FATAL, ERROR, WARN, INFO, DEBUG
	}
	private static final String TAG_DEBUG = "DEBUG";
	private static final String TAG_INFO = "INFO";
	private static final String TAG_WARN = "WARN";
	private static final String TAG_ERROR = "ERROR";
	private static final String TAG_FATAL = "FATAL";
	private static final String TAG_OFF = "OFF";

	
	public Logger(Formatter formatter, String levelName) {
		super();
		this.formatter = formatter;
		this.outputs = new ArrayList<Writable>();
		this.consoleActive = false;
		this.configLevel = new Level(levelName, levelValues.valueOf(levelName).ordinal());
		this.currentLevel = this.configLevel;
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
	
	public void on() {
		this.currentLevel = this.configLevel;
	}
	
	public void off() {
		this.currentLevel = new Level(TAG_OFF, levelValues.valueOf(TAG_OFF).ordinal());
	}
	
	public void debug(String logMsg) throws WriteException {
		Level level = new Level(TAG_DEBUG, levelValues.valueOf(TAG_DEBUG).ordinal());
		if (shouldLog(level)) {
			log(level.getName(), logMsg);
		}
	}

	public void info(String logMsg) throws WriteException {
		Level level = new Level(TAG_INFO, levelValues.valueOf(TAG_INFO).ordinal());
		if (shouldLog(level)) {
			log(level.getName(), logMsg);
		}
	}

	public void warn(String logMsg) throws WriteException {
		Level level = new Level(TAG_WARN, levelValues.valueOf(TAG_WARN).ordinal());
		if (shouldLog(level)) {
			log(level.getName(), logMsg);
		}
	}

	public void error(String logMsg) throws WriteException {
		Level level = new Level(TAG_ERROR, levelValues.valueOf(TAG_ERROR).ordinal());
		if (shouldLog(level)) {
			log(level.getName(), logMsg);
		}
	}

	public void fatal(String logMsg) throws WriteException {
		Level level = new Level(TAG_FATAL, levelValues.valueOf(TAG_FATAL).ordinal());
		if (shouldLog(level)) {
			log(level.getName(), logMsg);
		}
	}
	
	private Boolean shouldLog(Level level) {
		return this.currentLevel.isGreaterThan(level);
	}
	
	private void log(String level, String logMsg) throws WriteException {
		String formatedLog = formatter.giveFormat(level, logMsg);
		for (Writable output: outputs) {
			output.write(formatedLog);
		}
	}

}
