package com.fluid.authentication.model.dtos;

import lombok.Data;

import java.util.List;

@Data
public class RolePermissionDTO {
    private String roleName;
    private List<String> permissions;
}
