package com.fluid.authentication.model.entities;

import com.fluid.authentication.model.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


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

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

    public void setPermissions(Set<Permission> permissions) {
        if(this.permissions==null){
            this.permissions = new HashSet<>();
        }
        this.permissions.addAll(permissions);
    }

    public void addPermission(Permission permission){
        if(this.permissions==null){
            this.permissions = new HashSet<>();
        }
        this.permissions.add(permission);
    }

    public void removePermission(Permission permission){
        if(this.permissions==null){
            this.permissions = new HashSet<>();
        }
        this.permissions.remove(permission);
    }

}
