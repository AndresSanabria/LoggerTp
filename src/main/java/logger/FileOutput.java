package main.java.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.InvalidPathException;

public class FileOutput implements Writable {
	
	private String path;
	private FileWriter fileWriter;
	public static final String NULL_PATH = "Path given is NULL";
	public static final String WRONG_PATH = "Path given does not exist";
	
	public FileOutput(String filePath) throws IOException {
		setPath(filePath);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) throws IOException {
		if (path == null) {
			throw new InvalidPathException(path,NULL_PATH);
		}
		this.path = path;
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		this.fileWriter = new FileWriter(file,true);
	}
	
	public void write(String text) {		
  	  	BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
  	  	PrintWriter printWriter = new PrintWriter(bufferedWriter);
		printWriter.println(text);
  	  	printWriter.close();
	}

}
