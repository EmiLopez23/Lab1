package com.tradepal.TradePalApp.model;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(generator = "userIdGen",strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;
    private String password;
    private String email;

    @Transient

    private UUID token;

    private Roles role; //1:ADMIN, 0:USER in hsql table

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = Roles.USER;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Roles getRole() {
        return role;
    }
}
