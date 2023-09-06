package com.fluid.authentication.controller;

import com.fluid.authentication.annotation.AuthorizedAPI;
import com.fluid.authentication.jwt.JWTTokenService;
import com.fluid.authentication.model.dtos.PermissionDTO;
import com.fluid.authentication.service.RolePermissionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/")
@RequiredArgsConstructor
public class HomeController {
    private final RolePermissionService permissionService;
    private final JWTTokenService jwtTokenService;

    @GetMapping("home")
    @AuthorizedAPI
    public String printHome() {
        return "mazhar";
    }

    @GetMapping("permissions")
    @AuthorizedAPI
    public ResponseEntity<List<PermissionDTO>> userPermission(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            List<String> authorities = jwtTokenService.extractAuthorities(jwt);
            List<PermissionDTO> permissions = permissionService.getUserPermission(authorities);
            return ResponseEntity.ok(permissions);
        }
        else {
            return ResponseEntity.ok(List.of());
        }
    }

}
