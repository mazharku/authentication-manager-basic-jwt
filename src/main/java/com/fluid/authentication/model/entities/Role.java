package com.fluid.authentication.model.entities;

import com.fluid.authentication.model.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    private int id;

    @Column(name = "name", length = 64, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType name;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;

}
