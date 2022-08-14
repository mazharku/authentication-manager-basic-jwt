package com.fluid.authentication.service;

import com.fluid.authentication.jwt.JWTTokenService;
import com.fluid.authentication.model.dtos.CreateUserDTO;
import com.fluid.authentication.model.dtos.LoginModel;
import com.fluid.authentication.model.entities.Role;
import com.fluid.authentication.model.entities.User;
import com.fluid.authentication.model.enums.RoleType;
import com.fluid.authentication.repository.RoleRepository;
import com.fluid.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JWTTokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    @SneakyThrows
    public String getToken(LoginModel loginModel) {
        User user = userRepository.findByEmail(loginModel.getEmail())
                .orElseThrow(() -> new RuntimeException("invalid user!"));
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenService.generateToken(user);
    }

    public String createUser(CreateUserDTO userDTO) {
        String status = "created!";
        try {
            List<RoleType> roleNames = Arrays.stream(userDTO.getAuthorities().split("\\,"))
                    .map(e-> RoleType.valueOf(e.toUpperCase())).toList();
            List<Role> authorities = roleRepository.findAllByNameIn(roleNames);
            User user = new User();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(encoder.encode(userDTO.getPassword()));
            user.setActive(true);
            user.setAuthorities(authorities);
            userRepository.save(user);
            return status;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
