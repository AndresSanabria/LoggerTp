package main.java.logger;

public interface Writable {

	public void write(String text) throws WriteException;
	
	public String getStringId();
}
