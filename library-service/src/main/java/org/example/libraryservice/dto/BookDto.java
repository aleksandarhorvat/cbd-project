package org.example.libraryservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

@Value
public class BookDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 45)
    String title;
    @Size(max = 45)
    String genre;
    Integer yearWritten;
}