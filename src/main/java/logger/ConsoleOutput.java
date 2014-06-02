package main.java.logger;

/**
 * The Class ConsoleOutput manage the write of messages in Console Output.
 */
public class ConsoleOutput implements Writable {
	
	/** The Constant INVALID_STRING. */
	public static final String INVALID_STRING = "Invalid string"; 

	@Override
	public void write(String text) {
		if (text == null) {
			System.out.println(INVALID_STRING);
			return;
		}
		System.out.println(text);
	}

	@Override
	public String getStringId() {
		return "Console";
	}

}
