package org.example.libraryservice.repository;

import org.example.libraryservice.model.Loan;
import org.example.libraryservice.model.LoanId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, LoanId> {
  Optional<Loan> findById_CopyId(Integer copyId);
}