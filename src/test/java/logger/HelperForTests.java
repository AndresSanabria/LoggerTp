package logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import logger.writables.FileOutput;
import logger.writables.WriteException;

public class HelperForTests {
	
	public boolean stringInFile(String text, File file) throws FileNotFoundException {
		Scanner fileScanner = new Scanner(file);
		boolean found = false;
		while(fileScanner.hasNextLine()){
		     if(text.equals(fileScanner.nextLine().trim())){
		        found = true;
		        break;
		      }
		 }
		fileScanner.close();
		return found;
	}
	
	public void writeNewFileWithText(String filePath, String text) throws WriteException, IOException {
		File file = new File(filePath);
		file.delete();
		FileOutput configFile = new FileOutput(filePath);
		configFile.write(text);
	}

}
