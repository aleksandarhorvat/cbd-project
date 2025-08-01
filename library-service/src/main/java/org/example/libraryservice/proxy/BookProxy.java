package org.example.libraryservice.proxy;

import org.example.libraryservice.dto.BookDto;
import org.example.libraryservice.dto.CopyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("book-service")
public interface BookProxy {

    @GetMapping("/books")
    List<BookDto> getAllBooks();

    @GetMapping("/books/search")
    BookDto getBookByTitle(@RequestParam("title") String title);

    @GetMapping("/books/{id}/copies")
    List<CopyDto> getCopiesOfBook(@PathVariable("id") Integer bookId);

    @GetMapping("/books/copies/{id}")
    CopyDto getCopyById(@PathVariable("id") Integer copyId);
}