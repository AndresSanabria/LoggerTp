package main.java.logger;

public class DebugLogger extends Logger  {
	
	@Override
	public void debug(String logMsg){
		log("DEBUG",logMsg);
	}
	
	@Override
	public void info(String logMsg){
		log("INFO",logMsg);
	}
	
	@Override
	public void warn(String logMsg){
		log("WARN",logMsg);
	}
	
	@Override
	public void error(String logMsg){
		log("ERROR",logMsg);
	}
	
	@Override
	public void fatal(String logMsg){
		log("FATAL",logMsg);
	}

}
