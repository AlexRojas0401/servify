package com.servify.backend.application.service;

import com.servify.backend.domain.dto.request.RegisterUserRequest;
import com.servify.backend.domain.dto.response.UserResponse;
import com.servify.backend.domain.entity.Role;
import com.servify.backend.domain.entity.User;
import com.servify.backend.domain.entity.UserStatus;
import com.servify.backend.domain.repository.RoleRepository;
import com.servify.backend.domain.repository.UserRepository;
import com.servify.backend.domain.repository.UserStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserResponse createUser(RegisterUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }


        UserStatus activeStatus = userStatusRepository
                .findById((short) 1)
                .orElseThrow(() -> new RuntimeException("Active status not found"));


        Role clientRole = roleRepository
                .findById((short) 1)
                .orElseThrow(() -> new RuntimeException("Client role not found"));


        User user = User.builder()
                .id(UUID.randomUUID())
                .role(clientRole)
                .status(activeStatus)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .emailVerified(false)
                .phoneVerified(false)
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();


        User savedUser = userRepository.save(user);


        return mapToResponse(savedUser);
    }


    public User findByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }


    private UserResponse mapToResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole().getName())
                .status(user.getStatus().getName())
                .build();
    }

}