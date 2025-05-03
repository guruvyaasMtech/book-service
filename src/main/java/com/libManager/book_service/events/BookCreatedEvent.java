package com.libManager.book_service.events;

import java.io.Serializable;

public class BookCreatedEvent implements Serializable {
    private Long bookId;
    private String title;
    private String author;
    private String isbn;

    public BookCreatedEvent() {}

    public BookCreatedEvent(Long bookId, String title, String author, String isbn) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "BookCreatedEvent{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}