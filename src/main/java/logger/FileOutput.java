package main.java.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class FileOutput implements Writable {
	
	private String path;
	public static final String NULL_PATH = "Path given is NULL";
	public static final String WRONG_PATH = "Path given does not exist";
	
	public FileOutput(String filePath) throws IOException {
		setPath(filePath);
	}

	public String getPath() {
		return path;
	}

	private void setPath(String path) throws IOException {
		if (path == null) {
			throw new InvalidPathException(path,NULL_PATH);
		}
		this.path = path;
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}			
	}
	
	// TODO: Handle exception without modifying interface
	public void write(String text) throws WriteException {        
        try {
        	FileWriter fileWriter = new FileWriter(path,true);
        	BufferedWriter writer = new BufferedWriter(fileWriter);
			writer.append(text);
			writer.newLine();
	        writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new WriteException();
		}
        
	}

	@Override
	public String getStringId() {
		return "File: " + getPath();
	}

}
