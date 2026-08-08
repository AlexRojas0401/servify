package com.servify.backend.web.controller;

import com.servify.backend.domain.dto.request.RegisterUserRequest;
import com.servify.backend.domain.dto.response.UserResponse;
import com.servify.backend.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @RequestBody RegisterUserRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(request));
    }
}