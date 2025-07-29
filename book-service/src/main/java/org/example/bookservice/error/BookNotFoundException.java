package org.example.bookservice.error;

import java.util.NoSuchElementException;

public class BookNotFoundException extends NoSuchElementException {
    public BookNotFoundException(String s) {
        super(s);
    }
}
