package com.servify.backend.domain.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserResponse {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String role;

    private String status;
}