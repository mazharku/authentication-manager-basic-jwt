package com.fluid.authentication.service;

import com.fluid.authentication.model.dtos.PermissionDTO;
import com.fluid.authentication.model.dtos.RolePermissionDTO;
import com.fluid.authentication.model.entities.Permission;
import com.fluid.authentication.model.entities.Role;
import com.fluid.authentication.model.enums.RoleType;
import com.fluid.authentication.repository.PermissionRepository;
import com.fluid.authentication.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolePermissionService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public List<PermissionDTO> getPermissionsOfRole(String roleName) {
        RoleType roleType = RoleType.valueOf(roleName);
        Role role = roleRepository.getRoleByName(roleType)
                .orElseThrow(() -> new RuntimeException("undefined role"));
        return role.getPermissions()
                .stream().map(e -> new PermissionDTO(e.getId(), e.getName())).toList();
    }

    public boolean addPermissionOnRole(RolePermissionDTO rolePermission) {
        RoleType roleType = RoleType.valueOf(rolePermission.getRoleName());
        Role role = roleRepository.getRoleByName(roleType)
                .orElseThrow(() -> new RuntimeException("undefined role"));
        rolePermission.getPermissions()
                .forEach(p -> {
                    Permission permission = permissionRepository.getPermissionByName(p)
                            .orElse(createPermission(p));
                    role.addPermission(permission);
                });
        roleRepository.save(role);
        return true;
    }

    private Permission createPermission(String p) {
        Permission permission = new Permission();
        permission.setName(p);
        return permission;
    }

    public boolean revokePermissionOnRole(String roleName, String permissionName) {
        RoleType roleType = RoleType.valueOf(roleName);
        Role role = roleRepository.getRoleByName(roleType)
                .orElseThrow(() -> new RuntimeException("undefined role"));
        Permission permission = permissionRepository.getPermissionByName(permissionName)
                        .orElseThrow(()-> new RuntimeException("undefined permission"));
        role.removePermission(permission);
        roleRepository.save(role);
        return true;
    }
}
