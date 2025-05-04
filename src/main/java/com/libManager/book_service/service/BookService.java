package com.libManager.book_service.service;


import com.libManager.book_service.events.BookCreatedEvent;
import com.libManager.book_service.model.Book;
import com.libManager.book_service.repository.BookRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private final String BOOK_EXCHANGE = "book.exchange";
    private final String BOOK_CREATED_ROUTING_KEY = "book.created";


    @Transactional
    public Book createBook(Book book) {
        Book savedBook = bookRepository.save(book);
        BookCreatedEvent event = new BookCreatedEvent(
                savedBook.getId(), savedBook.getTitle(), savedBook.getAuthor(), savedBook.getIsbn());
        rabbitTemplate.convertAndSend(BOOK_EXCHANGE, BOOK_CREATED_ROUTING_KEY, event.getBookId());
        return savedBook;
    }


    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
}