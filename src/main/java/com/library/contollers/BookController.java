package com.library.contollers;

import com.library.models.Book;
import com.library.models.PaginatedResponse;
import com.library.services.BookService;
import com.library.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("books")
    public ResponseEntity<List<Book>> getAllBooks( ) {
        return bookService.getBooks();
    }

    @GetMapping("books/search")
    public PaginatedResponse<Book> getBookByTitle(
            @RequestParam(name = "title", required = false) String title, @RequestParam(name = "category", required = false) String category,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size) {

        return bookService.getBooksByTitle(title, category, page, size);
    }
    @GetMapping("books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(name = "id") long id)  {
        return bookService.getBookById(id);
    }


}
