package org.example.libraryservice.error;

import java.util.NoSuchElementException;

public class CopyNotFoundException extends NoSuchElementException {
    public CopyNotFoundException(String s) {super(s);}
}
