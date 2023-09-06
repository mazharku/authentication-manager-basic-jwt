package com.fluid.authentication.controller;

import com.fluid.authentication.annotation.ADMIN;
import com.fluid.authentication.model.dtos.PermissionDTO;
import com.fluid.authentication.model.dtos.RolePermissionDTO;
import com.fluid.authentication.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/")
@RequiredArgsConstructor
public class RolePermissionController {
    private final RolePermissionService service;

    @GetMapping("role-permission/{role}")
    public ResponseEntity<List<PermissionDTO>> getRolePermission(@PathVariable("role") String roleName){
        List<PermissionDTO> permissions = service.getPermissionsOfRole(roleName);
        return ResponseEntity.ok(permissions);
    }

    @PostMapping("role-permission")
    @ADMIN
    public ResponseEntity<?> addPermissionOnRole(@RequestBody RolePermissionDTO rolePermission) {
        boolean created= service.addPermissionOnRole(rolePermission);
        return ResponseEntity.ok(created?"permission added":"permission not added");
    }

    @PostMapping("role-permission/{role}/{permission}")
    @ADMIN
    public ResponseEntity<?> revokePermissionOnRole(@PathVariable("role")String role, @PathVariable("permission")String permission){
        boolean removed = service.revokePermissionOnRole(role,permission);
        return ResponseEntity.ok(removed?"permission removed":"permission not removed");
    }


}
