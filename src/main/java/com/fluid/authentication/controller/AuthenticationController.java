package com.fluid.authentication.controller;

import com.fluid.authentication.model.dtos.CreateUserDTO;
import com.fluid.authentication.model.dtos.LoginModel;
import com.fluid.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginModel loginModel) {
        return ResponseEntity.ok(service.getToken(loginModel));
    }

    @PostMapping("create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO userDTO) {
        return ResponseEntity.ok(service.createUser(userDTO));
    }

}
