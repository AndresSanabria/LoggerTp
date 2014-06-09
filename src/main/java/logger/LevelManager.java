package logger;

/**
 * The Class LevelManager stores the information of all the levels.
 */
public class LevelManager {

	/** The enum levels. */
	private enum levels { OFF, FATAL, ERROR, WARN, INFO, DEBUG, TRACE }


	/**
	 * Instantiates a new LevelManager.
	 */
	public LevelManager() {
		super();
	}

	/**
	 * Gets the value of a level.
	 *
	 * @param level the level from which to get the value
	 * @return the value of the level
	 */
	public final int getLevelValue(final String level) {
		return levels.valueOf(level).ordinal();
	}

}
