package main.java.logger;

public class ErrorLogger extends Logger {
	
	@Override
	public void error(String logMsg){
		log("ERROR",logMsg);
	}
	
	@Override
	public void fatal(String logMsg){
		log("FATAL",logMsg);
	}
	

}
