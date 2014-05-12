package main.java.logger;

public class WarnLogger extends Logger {

	public WarnLogger(Formatter formatter) {
		super(formatter);
	}

	@Override
	protected boolean shouldDebug() {
		return false;
	}

	@Override
	protected boolean shouldInfo() {
		return false;
	}

}
