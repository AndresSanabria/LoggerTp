package main.java.logger.useExample;

import java.io.IOException;

import main.java.logger.Logger;


public class Main {
	
	public static void main(String [] args) throws IOException // TODO: See where to catch exception 
	{
		Logger logger = new Logger("testConfig.properties");
		logger.debug("This message will not be logged because the level is info");
		logger.error("This is an example about how to use Logger");	
	}

}