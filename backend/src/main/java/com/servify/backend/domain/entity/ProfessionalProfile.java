package com.servify.backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "professional_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessionalProfile {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;


    @Column(name = "business_name", length = 150)
    private String businessName;


    @Column(columnDefinition = "TEXT")
    private String description;


    @Column(name = "experience_years")
    private Short experienceYears;


    @Column(nullable = false)
    private Boolean verified;


    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;


    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;


    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;
}