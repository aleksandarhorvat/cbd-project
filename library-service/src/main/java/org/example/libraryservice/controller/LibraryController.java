package org.example.libraryservice.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.libraryservice.dto.BookDto;
import org.example.libraryservice.model.Loan;
import org.example.libraryservice.service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
@Validated
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(libraryService.getAllBooks());
    }

    @GetMapping("/search")
    public ResponseEntity<BookDto> getBookByTitle(
            @NotBlank(message = "Title cannot be empty")
            @RequestParam("title") String title) {
        return ResponseEntity.ok(libraryService.getBookByTitle(title));
    }

    @PostMapping("/loan")
    public ResponseEntity<Loan> loanBook(
            @NotBlank(message = "Title cannot be empty")
            @RequestParam("title") String title,
            @Positive(message = "User ID must be positive")
            @RequestParam("userId") Integer userId) {
        return ResponseEntity.ok(libraryService.loanBook(title, userId));
    }

    @GetMapping("/admin/checkCopy")
    public ResponseEntity<String> checkCopy(
            @Positive(message = "Copy ID must be positive")
            @RequestParam("copyId") Integer copyId) {
        boolean isAvailable = libraryService.isCopyAvailable(copyId);
        return ResponseEntity.ok(isAvailable ? "Copy available" : "Copy not available");
    }
}