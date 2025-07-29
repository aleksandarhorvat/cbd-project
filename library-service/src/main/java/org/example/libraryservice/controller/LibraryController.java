package org.example.libraryservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.libraryservice.dto.BookDto;
import org.example.libraryservice.model.Loan;
import org.example.libraryservice.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(libraryService.getAllBooks());
    }

    @GetMapping("/search")
    public ResponseEntity<?> getBookByTitle(@RequestParam("title") String title) {
        try{
            return ResponseEntity.ok(libraryService.getBookByTitle(title));
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/loan")
    public ResponseEntity<?> loanBook(@RequestParam("title") String title, @RequestParam("UserId") Integer userId) {
        try {
            Loan loan = libraryService.loanBook(title, userId);
            return ResponseEntity.ok(loan);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
