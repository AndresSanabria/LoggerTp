package logger.filters;

/**
 * The Interface Filterer thought to filter messages.
 */
public interface Filterer {

	/**
	 * Filter determine if the given message must be filter or not.
	 *
	 * @param msg the msg
	 * @return true if message must be filter, otherwise return false
	 */
	Boolean filter(String msg);

}
