package com.argus.mytodo.entities;

import com.argus.mytodo.Enums.RoleENUM;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "user", schema = "mytodo")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    @Column(nullable = true, updatable = true, unique = true)
    private String picture;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleENUM role;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Authority> authorities;
}











