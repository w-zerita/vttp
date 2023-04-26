package vttp.ssf.poandquote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.StringReader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;

@SpringBootTest
@AutoConfigureMockMvc
public class TestQuoteController {
    @Autowired
    private MockMvc mockMvc;

    @Test // QuoteController
    public void shoudlReturn200() {

        // Build the request
        RequestBuilder req = MockMvcRequestBuilders.get("/quote")
            .accept(MediaType.TEXT_HTML_VALUE);

        // Call the controller
        MvcResult result = null;
        try {
            result = mockMvc.perform(req).andReturn();
        } catch (Exception e) {
            fail("cannot perform mvc invocation", e);
            return;
        }

        // Get response
        MockHttpServletResponse resp = result.getResponse();
        try {
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception e) {
            fail("cannot retrieve response payload", e);
            return;
        }
    }

    @Test // QuoteRestController
    public void shouldReturn10Quotes() {

        int count = 10;

        // Build the request
        RequestBuilder req = MockMvcRequestBuilders.get("/quote")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .queryParam("count", "%s".formatted(count)) // or append: "" + count
            .header("X-ID", "a header");
        
        // Call the REST controller
        MvcResult result = null;
        try {
            result = mockMvc.perform(req).andReturn();
        } catch (Exception e) {
            fail("cannot perform mvc invocation", e);
            return;
        }

        // Get response
        MockHttpServletResponse resp = result.getResponse();
        String payload;
        try {
            // JSON
            payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception e) {
            fail("cannot retrieve response payload", e);
            return;
        }

        JsonReader r = Json.createReader(new StringReader(payload));
        JsonArray quotes = r.readArray();

        assertEquals(count, quotes.size(), 
            "Expect %s quotes, Got %s".formatted(count, quotes.size()));
    }
}
