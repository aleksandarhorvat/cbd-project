package org.example.libraryservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

@Value
public class CopyDto implements Serializable {
    Integer id;
    @NotNull
    Integer book;
}