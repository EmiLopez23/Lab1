package com.tradepal.TradePalApp.responses;

import com.tradepal.TradePalApp.model.Role;
import com.tradepal.TradePalApp.model.User;

import java.util.Map;

public class AuthResponse {
    String token;
    Role userRole;

    public AuthResponse(String token, Role userRole) {
        this.token = token;
        this.userRole = userRole;
    }

    public String getToken() {
        return token;
    }

    public Role getUserRole() {
        return userRole;
    }
}
