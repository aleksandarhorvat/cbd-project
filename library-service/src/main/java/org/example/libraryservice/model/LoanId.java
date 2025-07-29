package org.example.libraryservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class LoanId implements Serializable {
    private static final long serialVersionUID = 1513232816839034230L;
    @NotNull
    @Column(name = "User_id", nullable = false)
    private Integer userId;

    @NotNull
    @Column(name = "Copy_id", nullable = false)
    private Integer copyId;

    public LoanId(Integer userId, Integer copyId) {
        this.userId = userId;
        this.copyId = copyId;
    }

    public LoanId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LoanId entity = (LoanId) o;
        return Objects.equals(this.copyId, entity.copyId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(copyId, userId);
    }

}