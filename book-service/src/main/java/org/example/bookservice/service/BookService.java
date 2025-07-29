package org.example.bookservice.service;

import lombok.RequiredArgsConstructor;
import org.example.bookservice.error.BookNotFoundException;
import org.example.bookservice.model.Book;
import org.example.bookservice.model.Copy;
import org.example.bookservice.repository.BookRepository;
import org.example.bookservice.repository.CopyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CopyRepository copyRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByTitle(String title) {
        return bookRepository.getBookByTitle(title).orElseThrow(() -> new BookNotFoundException("Performance with id " + title + " does not exist"));
    }

    public List<Copy> getCopiesOfBook(Integer bookId) {
        return copyRepository.getCopiesByBook(bookId);
    }
}
