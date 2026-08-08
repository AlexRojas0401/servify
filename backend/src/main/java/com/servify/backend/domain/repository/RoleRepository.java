package com.servify.backend.domain.repository;

import com.servify.backend.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Short> {

    Optional<Role> findByCode(String code);
}
