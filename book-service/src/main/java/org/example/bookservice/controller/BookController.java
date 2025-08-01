package org.example.bookservice.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.bookservice.model.Book;
import org.example.bookservice.model.Copy;
import org.example.bookservice.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
@Validated
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/search")
    public ResponseEntity<Book> getBookByTitle(
            @NotBlank(message = "Title cannot be empty")
            @RequestParam("title") String title) {
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }

    @GetMapping("/{id}/copies")
    public ResponseEntity<List<Copy>> getCopiesOfBook(
            @Positive(message = "Book ID must be positive")
            @PathVariable("id") Integer bookId) {
        return ResponseEntity.ok(bookService.getCopiesOfBook(bookId));
    }

    @GetMapping("/copies/{id}")
    public ResponseEntity<Copy> getCopyById(
            @Positive(message = "Copy ID must be positive")
            @PathVariable("id") Integer copyId) {
        return ResponseEntity.ok(bookService.getCopyById(copyId));
    }
}