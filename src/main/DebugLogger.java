
public class DebugLogger extends Logger  {
	
	public debug(String logMsg){
		log("DEBUG",String logMsg);
	}
	
	public info(String logMsg){
		log("INFO",String logMsg);
	}
	
	public warn(String logMsg){
		log("WARN",String logMsg);
	}
	
	public error(String logMsg){
		log("ERROR",String logMsg);
	}
	
	public fatal(String logMsg){
		log("FATAL",String logMsg);
	}

}
