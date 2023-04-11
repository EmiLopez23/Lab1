package com.tradepal.TradePalApp.responses;

import com.tradepal.TradePalApp.model.Role;
import com.tradepal.TradePalApp.model.User;

import java.util.Map;

public class AuthResponse {
    String token;
    Role role;

    public AuthResponse(String token, Role role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public Role getRole() {
        return role;
    }
}


