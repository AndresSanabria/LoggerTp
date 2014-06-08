package logger;

import static org.junit.Assert.*;
import logger.filters.Filterer;
import logger.filters.RegexFilter;

import org.junit.Test;

/**
 * The Class TestRegexFilter tests the RegExFilter.
 */
public class TestRegexFilter {

	/** The message. */
	private String msg = "Test - TRACE - Este es mi mensaje";


	@Test
	public final void filterMsgThatMatchRegex() {
		Filterer filterer = new RegexFilter(".*TRACE.*");
		assertTrue(filterer.filter(msg));
	}

	@Test
	public final void noFilterMsgThatDontMatchRegex() {
		Filterer filterer = new RegexFilter(".*Trace.*");
		assertFalse(filterer.filter(msg));
	}

}
