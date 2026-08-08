package com.servify.backend.application.service;


import com.servify.backend.domain.dto.request.RegisterRequest;
import com.servify.backend.domain.dto.response.RegisterResponse;
import com.servify.backend.domain.entity.Role;
import com.servify.backend.domain.entity.User;
import com.servify.backend.domain.entity.UserStatus;
import com.servify.backend.domain.repository.RoleRepository;
import com.servify.backend.domain.repository.UserRepository;
import com.servify.backend.domain.repository.UserStatusRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserStatusRepository userStatusRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserStatusRepository userStatusRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userStatusRepository = userStatusRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        // Comprobar si el email ya está registrado
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        // Comprobar si el teléfono ya está registrado
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new IllegalArgumentException("El teléfono ya está registrado");
        }

        // Buscar rol CLIENT
        Role role = roleRepository.findByCode("CLIENT")
                .orElseThrow(() ->
                        new IllegalStateException("No existe el rol CLIENT")
                );

        // Buscar estado PENDING_VERIFICATION
        UserStatus status = userStatusRepository.findByCode("PENDING_VERIFICATION")
                .orElseThrow(() ->
                        new IllegalStateException(
                                "No existe el estado PENDING_VERIFICATION"
                        )
                );

        // Crear usuario
        OffsetDateTime now = OffsetDateTime.now();

        User user = User.builder()
                .id(UUID.randomUUID())
                .role(role)
                .status(status)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .emailVerified(false)
                .phoneVerified(false)
                .profileImage(null)
                .createdAt(now)
                .updatedAt(now)
                .deletedAt(null)
                .build();

        // Guardar usuario
        User savedUser = userRepository.save(user);

        // Crear respuesta
        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getRole().getCode(),
                savedUser.getStatus().getCode()
        );
    }
}

