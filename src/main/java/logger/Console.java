package main.java.logger;

public class Console implements Writable {
	
	public static final String INVALID_STRING = "Invalid string"; 

	public void write(String text) {
		if (text == null) {
			System.out.println(INVALID_STRING);
			return;
		}
		System.out.println(text);
	}

}
