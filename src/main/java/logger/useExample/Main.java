package main.java.logger.useExample;

import main.java.logger.Logger;


public class Main {
	
	public static void main(String [] args)
	{
		System.out.println("current dir = " + System.getProperty("user.dir"));
		Logger logger = new Logger("configFiles/config.properties");
		logger.debug("This message will not be logged because the level is info");
		logger.error("This is an example about how to use Logger");	
	}

}