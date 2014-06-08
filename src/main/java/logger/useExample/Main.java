package logger.useExample;

import logger.Logger;
import logger.LoggerManager;

/**
 * The Class Main shows an example of how to use the logger.
 */
public final class Main {

	/**
	 * Instantiates Main.
	 *
	 */
	private Main() { }

	/**
	 * The main example.
	 *
	 * @param args the arguments with which to run main
	 */
	public static void main(final String [] args) {
		LoggerManager loggerManager = new LoggerManager("name");
		Logger logger = loggerManager.getLogger();
		logger.debug("This message will not be logged because the level is info");
		logger.error("This is an example about how to use Logger");
	}

}
