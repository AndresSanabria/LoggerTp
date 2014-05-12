package main.java.logger;

public class FatalLogger extends Logger {

	public FatalLogger(Formatter formatter) {
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

	@Override
	protected boolean shouldError() {
		return false;
	}

}
