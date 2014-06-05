package logger;

import static org.junit.Assert.*;

import logger.customFactory.CustomFactory;
import logger.customFactory.CustomFilterException;
import logger.filters.Filterer;
import logger.filters.RegexFilter;

import org.junit.Test;

public class TestCustomFilter {
	
	private CustomFactory factory = new CustomFactory();

	@Test
	public void createCustomRegexFilter() throws CustomFilterException {
		String[] params = new String[1];
		params[0] = ".*TRACE.*";
		Filterer filter = factory.createCustomFilter(RegexFilter.class.getName(), params);
		assertTrue(filter.filter("Test - TRACE - Este es mi mensaje"));
	}
	
	@Test(expected = CustomFilterException.class) 
	public void customFilterNotImplementingFilterer() throws CustomFilterException {
		factory.createCustomFilter(Level.class.getName(), new String[0]);
	}
	
}