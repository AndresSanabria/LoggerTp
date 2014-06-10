package logger.writables;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;

/**
 * The Class FileOutput manage the write of messages in Files.
 */
public class FileOutput implements Output {

	/** The path of the file where to write. */
	private String path;

	/** The Constant NULL_PATH. */
	public static final String NULL_PATH = "Path given is NULL";

	/** The Constant WRONG_PATH. */
	public static final String WRONG_PATH = "Path given does not exist";

	/**
	 * Instantiates a new file output.
	 *
	 * @param filePath the file path where to write
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public FileOutput(final String filePath) throws IOException {
		setPath(filePath);
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public final String getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 *
	 * @param path the path of the file where to write
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void setPath(final String path) throws IOException {
		if (path == null) {
			throw new InvalidPathException(path, NULL_PATH);
		}
		this.path = path;
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
	}

	@Override
	public final void write(final String text) throws OutputException {
		FileWriter fileWriter = null;
		BufferedWriter writer = null;
        try {
        	fileWriter = new FileWriter(path, true);
        	writer = new BufferedWriter(fileWriter);
			writer.append(text);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
			throw new OutputException();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new OutputException();
			}
		}
	}

	@Override
	public final String getStringId() {
		return "File: " + getPath();
	}

}
