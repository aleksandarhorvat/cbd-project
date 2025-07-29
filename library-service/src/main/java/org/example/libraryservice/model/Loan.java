package org.example.libraryservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "loan")
public class Loan {
    @EmbeddedId
    private LoanId id;

    @NotNull
    @Column(name = "loan_date", nullable = false)
    private LocalDate loanDate;

    @NotNull
    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    public Loan(LoanId loanId, LocalDate loanDate, LocalDate returnDate) {
        this.id = loanId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public Loan() {

    }
}