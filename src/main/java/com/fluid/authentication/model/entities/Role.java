package com.fluid.authentication.model.entities;

import com.fluid.authentication.model.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

}
