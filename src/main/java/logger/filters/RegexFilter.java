package logger.filters;

// TODO: Auto-generated Javadoc
/**
 * The Class RegexFilter determine if a message must be filter when it matches a regular expression.
 */
public class RegexFilter implements Filterer {

	/** The regular expression. */
	private String regex = ".*";
	
	/**
	 * Instantiates a new regex filter.
	 */
	public RegexFilter() {
		super();
	}

	/**
	 * Instantiates a new regex filter.
	 *
	 * @param regex the regex
	 */
	public RegexFilter(String regex) {
		super();
		this.regex = regex;
	}
	
	/**
	 * Sets the regular expression.
	 *
	 * @param regex the regex to filter by
	 */
	public void setRegex(String regex) {
		this.regex = regex;
	}
	
	@Override
	public Boolean filter(String msg) {
		return msg.matches(regex);
	}

}
