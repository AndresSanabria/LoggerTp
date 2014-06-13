package logger.filters;

/**
 * The Class RegexFilter determine if a message must be filter when it matches a regular expression.
 */
public class RegexFilter implements Filterer {

	/** The regular expression. */
	private String regex = ".*";

	/**
	 * Instantiates a new regex filter.
	 *
	 * @param regex the regex
	 */
	public RegexFilter(final String regex) {
		super();
		this.regex = regex;
	}

	@Override
	public final Boolean filter(final String msg) {
		return msg.matches(regex);
	}

}
