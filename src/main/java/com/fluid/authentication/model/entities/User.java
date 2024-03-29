package com.fluid.authentication.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "auth_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 256)
    private String name;

    @Column(name = "email", length = 256)
    private String email;

    @Column(name = "password", length = 1024)
    private String password;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> authorities;

}
