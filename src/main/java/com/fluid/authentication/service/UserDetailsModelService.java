package com.fluid.authentication.service;

import com.fluid.authentication.model.dtos.UserDetailsModel;
import com.fluid.authentication.model.entities.User;
import com.fluid.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsModelService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return new UserDetailsModel(new User());
        return new UserDetailsModel(user);
    }

}
