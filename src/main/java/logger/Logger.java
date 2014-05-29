package main.java.logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Logger {

	private Formatter formatter;
	private ConfigurationLoader configLoader;
	private List<Writable> outputs;
	private boolean consoleActive;
	private Level configLevel;
	private enum levelValues {
		OFF, FATAL, ERROR, WARN, INFO, DEBUG
	}
	private static final String WRITE_ERROR = "An error occured when writing log";

	
	public Logger(String configFilePath) {
		super();
		this.configLoader = new ConfigurationLoader(configFilePath);
		try{
			this.initializeOutputs();
		}
		catch(IOException e){
			handleException("There was an IOException when Intializing outputs: "+ e.getMessage());
			handleException("Check your Configuration file");
		}
		this.formatter = this.configLoader.initializeFormatter();
		this.consoleActive = false;
		String levelName = this.configLoader.getLevel();
		this.configLevel = new Level(levelName, levelValues.valueOf(levelName).ordinal());
	}
	
	private void initializeOutputs() throws IOException {
		this.outputs = new ArrayList<>();
		if (this.configLoader.getLogToConsole()) {
			this.enableConsoleOutput();
		}
		String[] fileOutputs = configLoader.getLogToFiles();
		if (fileOutputs != null) {
			for (String fileOutput: fileOutputs) {
				this.addOutput(new FileOutput(fileOutput));
			}
		}
	}
	
	private void enableConsoleOutput() {
		if (!consoleActive) {
			outputs.add(new ConsoleOutput());
			consoleActive = true;
		}
	}
	
	private void addOutput(Writable newOutput) {
		outputs.add(newOutput);
	}
	
	public void debug(String logMsg) {
		Level level = new Level(levelValues.DEBUG.name(), levelValues.DEBUG.ordinal());
		if (shouldLog(level)) {
			log(level, logMsg);
		}
	}

	public void info(String logMsg) {
		Level level = new Level(levelValues.INFO.name(), levelValues.INFO.ordinal());
		if (shouldLog(level)) {
			log(level, logMsg);
		}
	}

	public void warn(String logMsg) throws WriteException {
		Level level = new Level(levelValues.WARN.name(), levelValues.WARN.ordinal());
		if (shouldLog(level)) {
			log(level, logMsg);
		}
	}

	public void error(String logMsg) {
		Level level = new Level(levelValues.ERROR.name(), levelValues.ERROR.ordinal());
		if (shouldLog(level)) {
			log(level, logMsg);
		}
	}

	public void fatal(String logMsg) {
		Level level = new Level(levelValues.FATAL.name(), levelValues.FATAL.ordinal());
		if (shouldLog(level)) {
			log(level, logMsg);
		}
	}
	
	private Boolean shouldLog(Level level) {
		return this.configLevel.isGreaterThan(level);
	}
	
	private void log(Level level, String logMsg) {
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
