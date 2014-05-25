package main.java.logger;
import java.io.IOException;
import java.util.ArrayList;


public class Logger {

	private Formatter formatter;
	private ArrayList<Writable> outputs;
	private boolean consoleActive;
	private Level configLevel;
	private Level currentLevel;
	private enum levelValues {
		OFF, FATAL, ERROR, WARN, INFO, DEBUG
	}
	private static final String WRITE_ERROR = "An error occured when writing log";

	
	public Logger(Formatter formatter, String levelName) {
		super();
		this.formatter = formatter;
		this.outputs = new ArrayList<Writable>();
		this.consoleActive = false;
		this.configLevel = new Level(levelName, levelValues.valueOf(levelName).ordinal());
		this.currentLevel = this.configLevel;
	}
	
	public void enableConsoleOutput() {
		if (!consoleActive) {
			outputs.add(new ConsoleOutput());
			consoleActive = true;
		}
	}
	
	public void addFileOutput(String filePath) throws IOException {
		outputs.add(new FileOutput(filePath));
	}
	
	public void addOutput(Writable newOutput) {
		outputs.add(newOutput);
	}
	
	public void on() {
		this.currentLevel = this.configLevel;
	}
	
	public void off() {
		this.currentLevel = new Level(levelValues.OFF.name(), levelValues.OFF.ordinal());
	}
	
	public void debug(String logMsg) {
		Level level = new Level(levelValues.DEBUG.name(), levelValues.DEBUG.ordinal());
		if (shouldLog(level)) {
			log(level.getName(), logMsg);
		}
	}

	public void info(String logMsg) {
		Level level = new Level(levelValues.INFO.name(), levelValues.INFO.ordinal());
		if (shouldLog(level)) {
			log(level.getName(), logMsg);
		}
	}

	public void warn(String logMsg) throws WriteException {
		Level level = new Level(levelValues.WARN.name(), levelValues.WARN.ordinal());
		if (shouldLog(level)) {
			log(level.getName(), logMsg);
		}
	}

	public void error(String logMsg) {
		Level level = new Level(levelValues.ERROR.name(), levelValues.ERROR.ordinal());
		if (shouldLog(level)) {
			log(level.getName(), logMsg);
		}
	}

	public void fatal(String logMsg) {
		Level level = new Level(levelValues.FATAL.name(), levelValues.FATAL.ordinal());
		if (shouldLog(level)) {
			log(level.getName(), logMsg);
		}
	}
	
	private Boolean shouldLog(Level level) {
		return this.currentLevel.isGreaterThan(level);
	}
	
	private void log(String level, String logMsg) {
		String formatedLog = formatter.giveFormat(level, logMsg);
		for (Writable output: outputs) {
			try {
				output.write(formatedLog);
			} catch (WriteException e) {
				handleException(WRITE_ERROR + " - " + output.getStringId());
				e.printStackTrace();
			}
		}
	}
	
	private void handleException(String errorMessage) {
		System.err.println(errorMessage);
	}

}
