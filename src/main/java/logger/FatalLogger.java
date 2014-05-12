package main.java.logger;

public class FatalLogger extends Logger  {
	
	@Override
	public void fatal(String logMsg){
		log("FATAL",logMsg);
	}

}
