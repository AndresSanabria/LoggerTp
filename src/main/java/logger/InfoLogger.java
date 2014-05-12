package main.java.logger;

public class InfoLogger extends Logger {

	public InfoLogger(Formatter formatter) {
		super(formatter);
	}

	@Override
	protected boolean shouldDebug() {
		return false;
	}

}
