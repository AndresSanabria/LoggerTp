package logger.writables;

/**
 * The Interface Writable thought to write messages in different Outputs.
 */
public interface Writable {

	/**
	 * Write the text given in the Output of the Writable.
	 *
	 * @param text the text to write
	 * @throws WriteException when an error occurs while writing
	 */
	void write(String text) throws WriteException;

	/**
	 * Gets the string id.
	 *
	 * @return the string id
	 */
	String getStringId();
}
