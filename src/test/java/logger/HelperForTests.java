package logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import logger.writables.FileOutput;
import logger.writables.WriteException;

/**
 * The Class HelperForTests provides helper methods for tests.
 */
public class HelperForTests {

	/**
	 * Checks if the text is in the file.
	 *
	 * @param text the text to be sought
	 * @param file the file where to seek the text
	 * @return if the text is in the file
	 * @throws FileNotFoundException when the file is not found
	 */
	public final boolean stringInFile(final String text, final File file) throws FileNotFoundException {
		Scanner fileScanner = new Scanner(file);
		boolean found = false;
		while (fileScanner.hasNextLine()) {
		     if (text.equals(fileScanner.nextLine().trim())) {
		        found = true;
		        break;
		      }
		 }
		fileScanner.close();
		return found;
	}

	/**
	 * Creates and writes a file with text.
	 *
	 * @param filePath the path of the file where to write the text
	 * @param text the text to be written
	 * @throws WriteException when an error occurs while writing
	 * @throws IOException when an error occurs in an I/O operation
	 */
	public final void writeNewFileWithText(final String filePath, final String text) throws WriteException, IOException {
		File file = new File(filePath);
		file.delete();
		FileOutput configFile = new FileOutput(filePath);
		configFile.write(text);
	}

}
