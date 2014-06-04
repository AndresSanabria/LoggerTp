package logger;

import static org.junit.Assert.*;
import logger.filters.Filterer;
import logger.filters.RegexFilter;

import org.junit.Test;

public class TestRegexFilter {
	
	private String msg = "Test - TRACE - Este es mi mensaje";

	@Test
	public void filterMsgThatMatchRegex() {
		Filterer filterer = new RegexFilter(".*TRACE.*");
		assertTrue(filterer.filter(msg));
	}
	
	@Test
	public void noFilterMsgThatDontMatchRegex() {
		Filterer filterer = new RegexFilter(".*Trace.*");
		assertFalse(filterer.filter(msg));
	}
	
}
