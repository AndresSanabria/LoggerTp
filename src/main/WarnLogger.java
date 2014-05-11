
public class WarnLogger extends Logger {
	
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
