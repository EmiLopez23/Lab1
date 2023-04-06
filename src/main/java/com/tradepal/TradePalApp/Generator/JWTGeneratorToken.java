package com.tradepal.TradePalApp.Generator;

import com.tradepal.TradePalApp.model.User;

import java.util.Map;

public interface JWTGeneratorToken {

    String generateToken(User user);
}
