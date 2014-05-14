package main.java.logger;

public abstract class Formatter {
	
	protected String format;
	protected String separator = "-" ;
	
	public Formatter(String format) {
		super();
		this.format=format;
	}
	
	public Formatter(String format,String separator) {
		super();
		this.format=format;
		this.separator=separator;
	}

	public abstract String giveFormat(String level, String logMsg);
	
}
