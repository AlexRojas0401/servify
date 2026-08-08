package com.servify.backend.domain.repository;

import com.servify.backend.domain.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStatusRepository extends JpaRepository<UserStatus, Short> {

    Optional<UserStatus> findByCode(String code);
}
