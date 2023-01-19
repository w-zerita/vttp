package vttp2022.csf.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp2022.csf.backend.models.BookDetails;
import vttp2022.csf.backend.models.BookSummary;
import vttp2022.csf.backend.services.BookService;

@RestController
@RequestMapping(path = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksRESTController {
    
    @Autowired
    private BookService bookSvc;

    @GetMapping()
    public ResponseEntity<String> getBooks(@RequestParam(defaultValue="20") Integer limit, 
        @RequestParam(defaultValue="0") Integer offset) {

        List<BookSummary> summaries = bookSvc.search(limit, offset);
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (BookSummary summary: summaries)
            arrBuilder.add(summary.toJson());
        return ResponseEntity.ok(arrBuilder.build().toString());
    }

    @GetMapping(path = "/{bookId}")
    public ResponseEntity<String> getBookDetails(@PathVariable String bookId) {
        Optional<BookDetails> opt = bookSvc.getDetails(bookId);
        if (opt.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404");
        return ResponseEntity.ok(opt.get().toJson().toString());
    }

    @GetMapping(path = "/count")
    public ResponseEntity<String> getBooksCount() {
        Integer count = bookSvc.getTotalBooks();
        JsonObject bookCount = Json.createObjectBuilder()
            .add("count", count).build();
        return ResponseEntity.ok(bookCount.toString());
    }
}
