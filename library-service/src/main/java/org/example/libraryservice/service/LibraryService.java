package org.example.libraryservice.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.example.libraryservice.dto.BookDto;
import org.example.libraryservice.dto.CopyDto;
import org.example.libraryservice.dto.UserDto;
import org.example.libraryservice.error.BookNotAvailableException;
import org.example.libraryservice.error.BookNotFoundException;
import org.example.libraryservice.error.CopyNotFoundException;
import org.example.libraryservice.error.UserNotFoundException;
import org.example.libraryservice.model.*;
import org.example.libraryservice.proxy.BookProxy;
import org.example.libraryservice.proxy.UserProxy;
import org.example.libraryservice.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {
	
	@Autowired
    private final BookProxy bookProxy;
	@Autowired
    private final UserProxy userProxy;
	@Autowired
    private final LoanRepository loanRepository;
	@Autowired
    private final Clock clock;

    public List<BookDto> getAllBooks() {
        return bookProxy.getAllBooks();
    }

    public BookDto getBookByTitle(String title) {
        try {
            return bookProxy.getBookByTitle(title);
        } catch (FeignException.NotFound e) {
            throw new BookNotFoundException("Book with title '" + title + "' not found");
        }
    }

    public Loan loanBook(String title, Integer userId) {
        BookDto book;
        UserDto user;

        try {
            book = bookProxy.getBookByTitle(title);
        } catch (FeignException.NotFound e) {
            throw new BookNotFoundException("Book with title '" + title + "' not found");
        }

        try {
            user = userProxy.getUser(userId);
        } catch (FeignException.NotFound e) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }

        List<CopyDto> copies = bookProxy.getCopiesOfBook(book.getId());
        LocalDate now = LocalDate.now(clock);

        for (CopyDto copy : copies) {
            if (isCopyAvailable(copy.getId())) {
                Loan loan = new Loan(
                    new LoanId(user.getId(), copy.getId()),
                    now,
                    now.plusWeeks(1)
                );
                return loanRepository.save(loan);
            }
        }
        throw new BookNotAvailableException("No available copies for book: '" + title + "'");
    }

    public boolean isCopyAvailable(Integer copyId) {
        try {
            bookProxy.getCopyById(copyId);
            return loanRepository.findById_CopyId(copyId).isEmpty();
        } catch (FeignException.NotFound e) {
            throw new CopyNotFoundException("Copy not found with ID: " + copyId);
        }
    }
}