package org.example.libraryservice.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.example.libraryservice.dto.BookDto;
import org.example.libraryservice.dto.CopyDto;
import org.example.libraryservice.dto.UserDto;
import org.example.libraryservice.model.*;
import org.example.libraryservice.proxy.BookProxy;
import org.example.libraryservice.proxy.UserProxy;
import org.example.libraryservice.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookProxy bookProxy;
    private final UserProxy userProxy;
    private final LoanRepository loanRepository;

    public List<BookDto> getAllBooks() {
        return bookProxy.getAllBooks();
    }

    public BookDto getBookByTitle(String title) {
        try {
            return bookProxy.getBookByTitle(title);
        } catch (FeignException.NotFound e) {
            throw new NoSuchElementException("Book not found with title: " + title);
        }
    }

    public Loan loanBook(String title, Integer userId) {
        BookDto book = getBookByTitle(title);
        UserDto user;
        try {
            user = userProxy.getUser(userId);
        } catch (FeignException.NotFound e) {
            throw new NoSuchElementException("User with id " + userId + " not found in user-service");
        }
        List<CopyDto> copies = bookProxy.getCopiesOfBook(book.getId());
        for (CopyDto copy : copies) {
            if (isCopyAvailable(copy.getId())) {
                Loan loan = new Loan(new LoanId(user.getId(), copy.getId()), LocalDate.now(), LocalDate.now().plusWeeks(1));
                return loanRepository.save(loan);
            }
        }
        throw new RuntimeException("No available copies for book: " + title);
    }

    private boolean isCopyAvailable(Integer copyId) {
        return loanRepository.findById_CopyId(copyId).isEmpty();
    }
}
