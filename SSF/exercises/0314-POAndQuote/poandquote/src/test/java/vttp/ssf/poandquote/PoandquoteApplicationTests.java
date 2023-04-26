package vttp.ssf.poandquote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp.ssf.poandquote.services.QuoteService;

@SpringBootTest
class PoandquoteApplicationTests {

	@Autowired
	private QuoteService quoteSvc;

	@Test
	void contextLoads() {
		assertNotNull(quoteSvc);
	}

	@Test
	public void getQuotesShouldBeEqual() {
		int count = 4;
		Collection<String> result = quoteSvc.getQuotes(count);
		assertEquals(count, result.size(), 
			"getQuotes() does not return the expected count!");
	}
}
