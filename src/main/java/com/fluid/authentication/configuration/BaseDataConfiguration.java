package com.fluid.authentication.configuration;

import com.fluid.authentication.model.entities.Role;
import com.fluid.authentication.model.enums.RoleType;
import com.fluid.authentication.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BaseDataConfiguration {
    private final RoleRepository repository;
    @Value("${loadBaseData}")
    private boolean loadBaseData;

    @PostConstruct
    public void loadConfig() {
        if (loadBaseData) {
            loadBaseData();
        }
    }

    public void loadBaseData() {
        List<Role> authorities = Arrays.asList(
                new Role(1, RoleType.ADMIN, List.of()),
                new Role(2, RoleType.MANAGER, List.of()),
                new Role(3, RoleType.CASHIER, List.of()),
                new Role(4, RoleType.USER, List.of()));
        repository.saveAll(authorities);
    }

}
