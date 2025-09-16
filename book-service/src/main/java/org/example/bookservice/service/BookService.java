package org.example.bookservice.service;

import lombok.RequiredArgsConstructor;
import org.example.bookservice.error.BookNotFoundException;
import org.example.bookservice.error.CopyNotFoundException;
import org.example.bookservice.model.Book;
import org.example.bookservice.model.Copy;
import org.example.bookservice.repository.BookRepository;
import org.example.bookservice.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
	
	@Autowired
    private final BookRepository bookRepository;
	@Autowired
    private final CopyRepository copyRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByTitle(String title) {
        return bookRepository.getBookByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("Book with title '" + title + "' not found"));
    }

    public List<Copy> getCopiesOfBook(Integer bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException("Book with id " + bookId + " not found");
        }
        return copyRepository.getCopiesByBook(bookId);
    }

    public Copy getCopyById(Integer copyId) {
        return copyRepository.findById(copyId)
                .orElseThrow(() -> new CopyNotFoundException("Copy with id " + copyId + " not found"));
    }
}