package main.java.logger;

public interface Formatter {
	
	public String giveFormat(Level level, String logMsg);
	
}
