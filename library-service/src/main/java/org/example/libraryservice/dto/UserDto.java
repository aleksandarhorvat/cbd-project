package org.example.libraryservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

@Value
public class UserDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 45)
    String name;
    @NotNull
    @Size(max = 45)
    String username;
    @NotNull
    @Size(max = 255)
    String password;
    @NotNull
    @Size(max = 45)
    String address;
    @NotNull
    Integer role;
}