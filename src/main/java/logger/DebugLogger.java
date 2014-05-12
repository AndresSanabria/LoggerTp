package main.java.logger;

public class DebugLogger extends Logger {

	public DebugLogger(Formatter formatter) {
		super(formatter);
	}

	protected String getLevel() {
		return "DEBUG";
	}

}
