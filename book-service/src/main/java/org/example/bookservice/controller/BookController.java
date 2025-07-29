package org.example.bookservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.bookservice.error.BookNotFoundException;
import org.example.bookservice.model.Book;
import org.example.bookservice.model.Copy;
import org.example.bookservice.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/search")
    public ResponseEntity<?> getBookByTitle(@RequestParam("title") String title) {
        try{
            return ResponseEntity.ok(bookService.getBookByTitle(title));
        }catch (BookNotFoundException e){
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/copies/{bookId}")
    public ResponseEntity<List<Copy>> getCopiesOfBook(@PathVariable("bookId") Integer bookId) {
        return ResponseEntity.ok(bookService.getCopiesOfBook(bookId));
    }
}
