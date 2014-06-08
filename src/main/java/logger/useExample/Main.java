package logger.useExample;

import logger.Logger;

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
	public static void main(final String [] args)	{
		Logger logger = new Logger("name");
		logger.debug("This message will not be logged because the level is info");
		logger.error("This is an example about how to use Logger");
	}

}
