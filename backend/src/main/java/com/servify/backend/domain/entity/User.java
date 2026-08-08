package com.servify.backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private UserStatus status;


    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;


    @Column(name = "last_name", nullable = false, length = 150)
    private String lastName;


    @Column(nullable = false, unique = true, length = 255)
    private String email;


    @Column(name = "phone_number", nullable = false, unique = true, length = 20)
    private String phone;


    @Column(name = "password_hash", nullable = false, length = 255)
    private String password;


    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified;


    @Column(name = "phone_verified", nullable = false)
    private Boolean phoneVerified;


    @Column(name = "profile_image_url")
    private String profileImage;


    @Column(nullable = false)
    private OffsetDateTime createdAt;


    @Column(nullable = false)
    private OffsetDateTime updatedAt;


    @Column
    private OffsetDateTime deletedAt;
}