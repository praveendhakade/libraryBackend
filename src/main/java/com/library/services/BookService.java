package com.library.services;

import com.library.models.Book;
import com.library.models.PaginatedResponse;
import com.library.repository.BookRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRespository bookRespoitory;

    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookRespoitory.findAll();
        return ResponseEntity.ok(books);
    }

    public PaginatedResponse<Book> getBooksByTitle(String title, String category, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookRespoitory.findByTitleContainingOrCategoryContaining(title, category, pageable);
        long filteredTotal = books.getNumberOfElements();
        long totalCounts = bookRespoitory.count();
        long totalPages = books.getTotalPages();
        return new PaginatedResponse<>(totalCounts, filteredTotal, totalPages, books.getContent());
    }

    public ResponseEntity<Book> getBookById(long id) {
        Optional<Book> book = bookRespoitory.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
