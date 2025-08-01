package org.example.libraryservice.error;

import java.util.NoSuchElementException;

public class BookNotFoundException extends NoSuchElementException {
    public BookNotFoundException(String s) {
        super(s);
    }
}