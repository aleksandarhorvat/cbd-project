package org.example.bookservice.repository;

import jakarta.validation.constraints.NotNull;
import org.example.bookservice.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CopyRepository extends JpaRepository<Copy, Integer> {
  List<Copy> getCopiesByBook(Integer book);
}