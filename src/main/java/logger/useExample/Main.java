package logger.useExample;

import logger.Logger;


public class Main {
	
	public static void main(String [] args)
	{
		Logger logger = new Logger("name");
		logger.debug("This message will not be logged because the level is info");
		logger.error("This is an example about how to use Logger");	
	}

}