package org.callforcode.healthcare.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "Role")
public class Role implements GrantedAuthority {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoles() {
        return role;
    }

    public void setRoles(String roles) {
        this.role = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "role")
    private String role;

    @Override
    @Transient
    public String getAuthority() {
        return getRoles();
    }
}

