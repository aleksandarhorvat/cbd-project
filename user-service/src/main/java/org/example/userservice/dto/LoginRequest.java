package org.example.userservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class LoginRequest {
    @NotNull
    @Size(max = 45)
    String username;
    @NotNull
    @Size(max = 255)
    String password;
}