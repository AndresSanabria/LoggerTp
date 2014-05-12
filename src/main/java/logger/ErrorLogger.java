package main.java.logger;

public class ErrorLogger extends Logger {

	public ErrorLogger(Formatter formatter) {
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

	@Override
	protected boolean shouldWarn() {
		return false;
	}

}
