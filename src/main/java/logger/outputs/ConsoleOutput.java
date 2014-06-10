package logger.outputs;

/**
 * The Class ConsoleOutput manage the write of messages in Console Output.
 */
public class ConsoleOutput implements Output {

	/** The Constant INVALID_STRING. */
	public static final String INVALID_STRING = "Invalid string";

	@Override
	public final void write(final String text) {
		if (text == null) {
			System.out.println(INVALID_STRING);
			return;
		}
		System.out.println(text);
	}

	@Override
	public final String getStringId() {
		return "Console";
	}

}
