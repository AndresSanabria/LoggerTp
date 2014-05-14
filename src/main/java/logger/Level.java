package main.java.logger;


public class Level {

	private String name;
	private int value;
	
	
	public Level(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getValue() {
		return value;
	}
	
}
