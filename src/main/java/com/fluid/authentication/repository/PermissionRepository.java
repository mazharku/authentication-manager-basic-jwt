package com.fluid.authentication.repository;

import com.fluid.authentication.model.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Optional<Permission> getPermissionByName(String name);
}
