
public abstract class Logger {
	
	public void debug(String logMsg){
		
	}
	
	public void info(String logMsg){
		
	}
	
	public void warn(String logMsg){
		
	}
	
	public void error(String logMsg){
		
	}
	
	public void fatal(String logMsg){
		
	}
	
	protected log(String level,String logMsg){
		//TODO:Escribir en los outputs el msg 
		System.out.println("["+level+"]:" + logMsg);
	}

}
