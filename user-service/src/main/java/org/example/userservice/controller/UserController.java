package org.example.userservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.userservice.model.User;
import org.example.userservice.dto.UserDto;
import org.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.example.userservice.dto.LoginRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Validated
public class UserController {
	
	@Autowired
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.registerUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(
            @Positive(message = "User ID must be positive")
            @PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

}
