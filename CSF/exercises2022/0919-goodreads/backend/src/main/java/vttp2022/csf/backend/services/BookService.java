package vttp2022.csf.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.csf.backend.models.BookDetails;
import vttp2022.csf.backend.models.BookSummary;
import vttp2022.csf.backend.repositories.BookRepository;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepo;

    public List<BookSummary> search(Integer limit, Integer offset) {
        return bookRepo.getBooks(limit, offset);
    }

    public Optional<BookDetails> getDetails(String bookId) {
        return bookRepo.getBookDetailsById(bookId);
    }

    public Integer getTotalBooks() {
        return bookRepo.getTotalBooksCount();
    }
}
