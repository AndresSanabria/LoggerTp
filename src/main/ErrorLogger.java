
public class ErrorLogger extends Logger {
	
	public error(String logMsg){
		log("ERROR",String logMsg);
	}
	
	public fatal(String logMsg){
		log("FATAL",String logMsg);
	}
	

}
