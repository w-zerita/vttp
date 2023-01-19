package vttp2022.csf.backend.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.csf.backend.models.BookDetails;
import vttp2022.csf.backend.models.BookSummary;

@Repository
public class BookRepository {

    private static final String SQL_GET_BOOKS = 
        "select book_id, title from book2018 order by title asc limit ? offset ?";

    private static final String SQL_GET_BOOK_DETAILS_BY_ID = 
        "select * from book2018 where book_id = ?";

    private static final String SQL_GET_TOTAL_BOOKS = 
        "select count(*) from book2018";
    
    @Autowired
    private JdbcTemplate template;

    public Integer getTotalBooksCount() {
        Integer count = template.queryForObject(
            SQL_GET_TOTAL_BOOKS, Integer.class);
        return count;
    }

    public List<BookSummary> getBooks(Integer limit, Integer offset) {
        List<BookSummary> summaries = new ArrayList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GET_BOOKS, limit, offset);
        while(rs.next()) {
            BookSummary summary = BookSummary.create(rs);
            summaries.add(summary);
        }
        return summaries;
    }

    public Optional<BookDetails> getBookDetailsById(String bookId) {
        BookDetails details = new BookDetails();
        SqlRowSet rs = template.queryForRowSet(SQL_GET_BOOK_DETAILS_BY_ID, bookId);
        while (rs.next()) {
            details = BookDetails.create(rs);
            return Optional.of(details);
        }
        return Optional.empty();
    }
}
