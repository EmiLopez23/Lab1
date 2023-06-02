package com.tradepal.TradePalApp.responses;

import com.tradepal.TradePalApp.model.Role;
import com.tradepal.TradePalApp.model.User;

import java.util.Map;

public class AuthResponse {
    String token;
    Role role;

    Long id;
    String username;

    public AuthResponse(String token, Role role, String username, Long id) {
        this.token = token;
        this.role = role;
        this.username = username;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public Role getRole() {
        return role;
    }

    public String getUsername(){return username;}

    public Long getId() {
        return id;
    }
}


