package main.java.logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Logger logs messages in the given outputs with the proper format.
 */
public class Logger {

	/** The formatter. */
	private Formatter formatter;
	
	/** The configuration loader. */
	private ConfigurationLoader configLoader;
	
	/** The outputs where to log */
	private List<Writable> outputs;
	
	/** Is the console active? */
	private boolean consoleActive;
	
	/** The configuration level. */
	private Level configLevel;
	
	/**
	 * The Enum levelValues.
	 */
	private enum levelValues {
		OFF,
		FATAL, 
		ERROR, 
		WARN, 
		INFO, 
		DEBUG,
		TRACE
	}
	
	/** The Constant WRITE_ERROR. */
	private static final String WRITE_ERROR = "An error occured when writing log";

	
	/**
	 * Instantiates a new logger.
	 *
	 * @param configFilePath the configuration file path
	 */
	public Logger() {
		super();
		this.configLoader = new ConfigurationLoader();
		try{
			this.initializeOutputs();
		}
		catch(IOException e){
			handleException("There was an IOException when Intializing outputs: "+ e.getMessage()+"\n Check your Configuration file");
		}
		this.formatter = this.configLoader.initializeFormatter();
		this.consoleActive = false;
		String levelName = this.configLoader.getConfiguration().getLevel();
		this.configLevel = new Level(levelName, levelValues.valueOf(levelName).ordinal());
	}
	
	/**
	 * Initialize outputs.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void initializeOutputs() throws IOException {
		this.outputs = new ArrayList<>();
		if (this.configLoader.getConfiguration().getLogToConsole()) {
			this.enableConsoleOutput();
		}
		String[] fileOutputs = configLoader.getConfiguration().getLogToFiles();
		if (fileOutputs != null) {
			for (String fileOutput: fileOutputs) {
				this.addOutput(new FileOutput(fileOutput));
			}
		}
	}
	
	/**
	 * Enable console output.
	 */
	private void enableConsoleOutput() {
		if (!consoleActive) {
			outputs.add(new ConsoleOutput());
			consoleActive = true;
		}
	}
	
	/**
	 * Adds the output where to Log.
	 *
	 * @param newOutput the new output to be add
	 */
	private void addOutput(Writable newOutput) {
		outputs.add(newOutput);
	}
	
	/**
	 * Log in Trace Level.
	 *
	 * @param logMsg the message to log
	 */
	public void trace(String logMsg) {
		Level level = new Level(levelValues.TRACE.name(), levelValues.TRACE.ordinal());
		if (shouldLog(level)) {
			log(level, logMsg);
		}
	}
	
	/**
	 * Log in Debug Level.
	 *
	 * @param logMsg the message to log
	 */
	public void debug(String logMsg) {
		Level level = new Level(levelValues.DEBUG.name(), levelValues.DEBUG.ordinal());
		if (shouldLog(level)) {
			log(level, logMsg);
		}
	}

	/**
	 * Log in Info Level.
	 *
	 * @param logMsg the message to log
	 */
	public void info(String logMsg) {
		Level level = new Level(levelValues.INFO.name(), levelValues.INFO.ordinal());
		if (shouldLog(level)) {
			log(level, logMsg);
		}
	}

	/**
	 * Log in Warn Level.
	 *
	 * @param logMsg the message to log
	 */
	public void warn(String logMsg) {
		Level level = new Level(levelValues.WARN.name(), levelValues.WARN.ordinal());
		if (shouldLog(level)) {
			log(level, logMsg);
		}
	}

	/**
	 * Log in Error Level.
	 *
	 * @param logMsg the message to log
	 */
	public void error(String logMsg) {
		Level level = new Level(levelValues.ERROR.name(), levelValues.ERROR.ordinal());
		if (shouldLog(level)) {
			log(level, logMsg);
		}
	}

	/**
	 * Log in Fatal Level.
	 *
	 * @param logMsg the message to log
	 */
	public void fatal(String logMsg) {
		Level level = new Level(levelValues.FATAL.name(), levelValues.FATAL.ordinal());
		if (shouldLog(level)) {
			log(level, logMsg);
		}
	}
	
	/**
	 * Should the Logger log in this Level.
	 *
	 * @param level the level to check if should log
	 * @return true if should log, otherwise false
	 */
	private Boolean shouldLog(Level level) {
		return this.configLevel.isGreaterThan(level);
	}
	
	/**
	 * Log the message in the given Level.
	 *
	 * @param level the level in which to log
	 * @param logMsg the message to log
	 */
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
	
	/**
	 * Handle exception caught.
	 *
	 * @param errorMessage the error message
	 */
	private void handleException(String errorMessage) {
		System.err.println(errorMessage);
	}

}
