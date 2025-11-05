package com.example.recipebackend.domain;

import java.util.Set;

/**
 * User domain entity (in-memory). JPA-ready fields are present but not annotated to keep it simple.
 */
public class User {
    private Long id;
    private String username;
    private String email;
    private String passwordHash;
    private Set<String> roles;

    public User() {}

    public User(Long id, String username, String email, String passwordHash, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id; return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username; return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email; return this;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public User setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash; return this;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public User setRoles(Set<String> roles) {
        this.roles = roles; return this;
    }
}
