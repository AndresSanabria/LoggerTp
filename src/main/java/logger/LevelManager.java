package logger;

/**
 * The Class LevelManager stores the information of all the levels.
 */
public class LevelManager {

	/** The enum levels. */
	private enum levels { 
 /** The off. */
 OFF, 
 /** The fatal. */
 FATAL, 
 /** The error. */
 ERROR, 
 /** The warn. */
 WARN, 
 /** The info. */
 INFO, 
 /** The debug. */
 DEBUG, 
 /** The trace. */
 TRACE }


	/**
	 * Instantiates a new LevelManager.
	 */
	public LevelManager() {
		super();
	}
	
	/**
	 * Gets the level.
	 *
	 * @param String from which to get the Level
	 * @return the level
	 */
	public final Level getLevel(final String level) {
		return new Level(level, levels.valueOf(level).ordinal());
	}

}
