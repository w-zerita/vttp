package vttp.ssf.poandquote.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.ssf.poandquote.services.QuoteService;

@RestController // HTML content negotiation - manipulate resources through representation
@RequestMapping(path = "quote", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuoteRESTController {
    @Autowired
    private QuoteService quoteSvc;

    // Query parameter count = number
    // If count is not available, count = 1
    @GetMapping
    public ResponseEntity<String> getQuote(
        @RequestHeader(name = "X-ID", required = false) String id,
        @RequestParam(name = "count", defaultValue = "1") Integer count) {

            Collection<String> quotes = quoteSvc.getQuotes(count);
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

            // Highway
            quotes.stream().filter((String q) -> {
                return q.length() > 20;
            }).map((String q) -> {
                return Json.createObjectBuilder()
                    .add("quote", q)
                    .add("timestamp", System.currentTimeMillis()) // Epoch Unix Timestamp
                    .build();
            }).forEach((JsonObject o) -> {
                arrBuilder.add(o);
            });

            // using List
            /*
            List<JsonObject> result = quotes.stream()
                .filter((String q) -> {
                    return q.length() > 20;
                })
                .map((String q) -> {
                    return Json.createObjectBuilder()
                        .add("quote", q)
                        .add("timestamp", System.currentTimeMillis()) // Epoch Unix Timestamp
                        .build();
                })
                .toList();
            */

            // loops from start to end sequentially, cannot take advantage of multi-processor
            // Carpark
            /*
            for (String q: quotes) {
                JsonObject result = Json.createObjectBuilder()
                    .add("quote", q)
                    .add("timestamp", System.currentTimeMillis()) // Epoch Unix Timestamp
                    .build();
                arrBuilder.add(result);
            } 
            */
            JsonArray quoteArray = arrBuilder.build();

            return ResponseEntity
                .ok()
                .header("X-ID", id)
                .header("X-My-Header", "Powered by SpringBoot")
                .body(quoteArray.toString()); // match ResponseEntity<String>
    }
}
