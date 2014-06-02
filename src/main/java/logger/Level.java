package main.java.logger;

/**
 * The Class Level.
 */
public class Level {

	/** The name of the Level. */
	private String name;
	
	/** The value of the Level. */
	private int value;
	
	
	/**
	 * Instantiates a new level.
	 *
	 * @param name the name of the Level
	 * @param value the value of the Level
	 */
	public Level(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Gets the name of the Level.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the value of the Level.
	 *
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Checks if the Level is greater than another one.
	 *
	 * @param level the level to compare with
	 * @return True if greater than, otherwise false
	 */
	public Boolean isGreaterThan(Level level) {
		return (this.getValue() >= level.getValue());
	}
	
}
