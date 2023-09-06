package com.fluid.authentication.service;

import com.fluid.authentication.model.dtos.PermissionDTO;
import com.fluid.authentication.model.dtos.RolePermissionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RolePermissionService {
    public List<PermissionDTO> getPermissionsOfRole(String roleName) {
        return new ArrayList<>();
    }

    public boolean addPermissionOnRole(RolePermissionDTO rolePermission) {
        return true;
    }

    public boolean revokePermissionOnRole(String role, String permission) {
        return true;
    }
}
