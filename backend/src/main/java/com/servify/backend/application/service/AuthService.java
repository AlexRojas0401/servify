package com.servify.backend.application.service;

import com.servify.backend.domain.dto.request.LoginRequest;
import com.servify.backend.domain.dto.request.RegisterRequest;
import com.servify.backend.domain.dto.response.LoginResponse;
import com.servify.backend.domain.dto.response.RegisterResponse;
import com.servify.backend.domain.entity.User;
import com.servify.backend.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return new RegisterResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole() != null ? user.getRole().getCode() : null,
                user.getStatus() != null ? user.getStatus().getCode() : null
        );
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Email o contraseña incorrectos"
                        )
                );

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new IllegalArgumentException(
                    "Email o contraseña incorrectos"
            );
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(
                "Login correcto",
                user.getEmail(),
                token
        );
    }
}