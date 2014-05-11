
public class InfoLogger extends Logger  {
	
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
