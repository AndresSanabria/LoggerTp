package main.java.logger.useExample;

import java.io.IOException;

import main.java.logger.Logger;
import main.java.logger.LoggerManager;

public class Main {
	
	public static void main(String [] args) throws IOException // TODO: See where to catch exception 
	{
		LoggerManager manager = new LoggerManager();
		manager.loadConfiguration("testConfig.properties");
		Logger logger = manager.getLogger();
		logger.debug("This message will not be logged because the level is info");
		logger.error("This is an example about how to use Logger");	
	}

}
