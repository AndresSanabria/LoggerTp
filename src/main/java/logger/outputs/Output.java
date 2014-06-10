package logger.outputs;

/**
 * The Interface Output thought to write messages in different Outputs.
 */
public interface Output {

	/**
	 * Write the text given in the Output of the Output.
	 *
	 * @param text the text to write
	 * @throws OutputException when an error occurs while writing
	 */
	void write(String text) throws OutputException;

	/**
	 * Gets the string id.
	 *
	 * @return the string id
	 */
	String getStringId();
}
