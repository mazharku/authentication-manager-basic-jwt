package com.fluid.authentication.repository;

import com.fluid.authentication.model.entities.Role;
import com.fluid.authentication.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findAllByNameIn(List<RoleType> roleTypes);

    Optional<Role> getRoleByName(RoleType roleName);
}
