package main.java.logger;

import java.util.HashMap;
import java.util.Map;

public abstract class Formatter {

	// Positions are -1 if must no be log
	// TODO: Ver si hay alguna forma de no tener tantos atributos primitivos
	private Integer logLevelPosition;
	private Integer threadNamePosition;
	private Integer logMessagePosition;
	private Integer lineNumberPosition;
	private Integer fileNamePosition;
	private Integer methodNamePosition;
	private Integer datePosition;

	protected Character separator;

	public Formatter() {
		super();
		logLevelPosition = -1;
		threadNamePosition = -1;
		logMessagePosition = -1;
		lineNumberPosition = -1;
		fileNamePosition = -1;
		methodNamePosition = -1;
		datePosition = -1;
		separator = '-';
	}

	public void setLogLevelPosition(Integer logLevelPosition) {
		this.logLevelPosition = logLevelPosition;
	}

	public void setThreadNamePosition(Integer threadNamePosition) {
		this.threadNamePosition = threadNamePosition;
	}

	public void setLogMessagePosition(Integer logMessagePosition) {
		this.logMessagePosition = logMessagePosition;
	}

	public void setLineNumberPosition(Integer lineNumberPosition) {
		this.lineNumberPosition = lineNumberPosition;
	}

	public void setFileNamePosition(Integer fileNamePosition) {
		this.fileNamePosition = fileNamePosition;
	}

	public void setMethodNamePosition(Integer methodNamePosition) {
		this.methodNamePosition = methodNamePosition;
	}

	public void setDatePosition(Integer datePosition) {
		this.datePosition = datePosition;
	}

	public void setSeparator(Character separator) {
		this.separator = separator;
	}

	protected Map<Integer, String> getOrderedInfoToFormat(String level,
			String log, String fileName, String methodName, Integer lineNumber) {
		Map<Integer, String> orderedInfo = new HashMap<Integer, String>();
		addLogItem(orderedInfo, level, logLevelPosition);
		// addLogItem(orderedInfo, threadName, threadNamePosition);
		addLogItem(orderedInfo, log, logMessagePosition);
		addLogItem(orderedInfo, lineNumber.toString(), lineNumberPosition);
		addLogItem(orderedInfo, fileName, fileNamePosition);
		addLogItem(orderedInfo, methodName, methodNamePosition);
		// addLogItem(orderedInfo, dateName, datePosition);

		return orderedInfo;
	}

	private void addLogItem(Map<Integer, String> orderedInfo, String log,
			Integer position) {
		if (position.equals(-1) || log == null) {
			return;
		}
		orderedInfo.put(position, log);
	}

	public abstract String giveFormat(String level, String logMsg);

	public abstract String giveFormat(String level, String log,
			String fileName, String methodName, Integer lineNumber);
}
