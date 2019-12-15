package com.furrychickens.natlib.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long bookId) {
        super("Book with id " + bookId + " does not exist");
    }
}
