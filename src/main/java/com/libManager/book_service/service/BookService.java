package com.libManager.book_service.service;

import com.libManager.book_service.model.Book;
import com.libManager.book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public void addBook(Book book) {
        // Add any business logic here, e.g., checking if a book with the same ISBN already exists
        bookRepository.save(book);
    }

    public void updateBook(Long id, Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(id);
        existingBook.ifPresent(book -> {
            updatedBook.setId(book.getId()); // Ensure the ID is set for updating
            bookRepository.save(updatedBook);
        });
        // You might want to handle the case where the book doesn't exist
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}