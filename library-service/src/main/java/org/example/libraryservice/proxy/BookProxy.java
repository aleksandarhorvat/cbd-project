package org.example.libraryservice.proxy;

import org.example.libraryservice.dto.BookDto;
import org.example.libraryservice.dto.CopyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("book-service")
public interface BookProxy {

    @GetMapping("/books")
    public List<BookDto> getAllBooks();

    @GetMapping("/books/search")
    public BookDto getBookByTitle(@RequestParam("title") String title);

    @GetMapping("/books/copies/{bookId}")
    public List<CopyDto> getCopiesOfBook(@PathVariable("bookId") Integer bookId);
}
