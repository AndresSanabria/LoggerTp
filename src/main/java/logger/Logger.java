package main.java.logger;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Logger {

	private Formatter formatter;
	private ArrayList<Writable> outputs;
	private boolean consoleActive;
	private static final String TAG_DEBUG = "DEBUG";
	private static final String TAG_INFO = "INFO";
	private static final String TAG_WARN = "WARN";
	private static final String TAG_ERROR = "ERROR";
	private static final String TAG_FATAL = "FATAL";

	public Logger(Formatter formatter) {
		super();
		this.formatter = formatter;
		this.outputs = new ArrayList<Writable>();
		consoleActive = false;
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
		if (shouldDebug()) {
			log(TAG_DEBUG, logMsg);
		}
	}

	public void info(String logMsg) throws WriteException {
		if (shouldInfo()) {
			log(TAG_INFO, logMsg);
		}
	}

	public void warn(String logMsg) throws WriteException {
		if (shouldWarn()) {
			log(TAG_WARN, logMsg);
		}
	}

	public void error(String logMsg) throws WriteException {
		if (shouldError()) {
			log(TAG_ERROR, logMsg);
		}
	}

	public void fatal(String logMsg) throws WriteException {
		if (shouldFatal()) {
			log(TAG_FATAL, logMsg);
		}
	}

	/*
	 * Se imprime en todos los output, no hay que decidir en cual dando metodos distintos
	 * 
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
	*/
	
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

	private void log(String level, String logMsg) throws WriteException {
		// El formatter deberia recibir el level y ya formar el mensaje final
		String formatedLog = formatter.giveFormat(level, logMsg);
		// Esta logica deberia ir adentro del formatter
		String finalMsg = "[" + level + "]:" + formatedLog;
		for (Writable output: outputs) {
			output.write(finalMsg);
		}
	}

	/*
	// Este metodo ya no tiene sentido, tenes una lista generica de outputs no hay que distinguir con file y console
	private void log(String level, String logMsg, String fileName,
			String methodName, Integer lineNumber) {
		String formatedLog = formatter.giveFormat(level, logMsg, fileName,
				methodName, lineNumber);
		// TODO:Escribir en los outputs el msg
		System.out.println("[" + level + "]:" + formatedLog);
	}
	*/
}
