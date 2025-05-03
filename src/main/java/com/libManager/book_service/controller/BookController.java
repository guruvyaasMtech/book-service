package com.libManager.book_service.controller;

import com.libManager.book_service.model.Book;
import com.libManager.book_service.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Import all annotations
import java.util.Optional;

@RestController
@RequestMapping("/books") //  <---  Crucial:  Class-level mapping
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping //  <--- Method-level mapping for POST
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @GetMapping("/{id}")  //  <--- Method-level mapping for GET with ID
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

