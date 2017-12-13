package com.heypulse.api.rest.entity;

import com.heypulse.api.rest.model.Role;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by emrahsoytekin on 22.10.2017.
 */
@Entity
@Table(name = "C_USER_ROLE")
public class UserRole implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    @Column(name = "USERNAME")
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
