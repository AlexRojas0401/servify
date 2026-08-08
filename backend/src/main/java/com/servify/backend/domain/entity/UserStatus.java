package com.servify.backend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "user_statuses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStatus {


    @Id
    private Short id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private OffsetDateTime createdAt;


}
