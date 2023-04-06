package com.tradepal.TradePalApp.Generator;

import com.tradepal.TradePalApp.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTGeneratorTokenImpl implements JWTGeneratorToken{


    @Value("${app.jwttoken.message}")
    private String message;

    public static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    @Override
    public String generateToken(User user) {
        String jwtToken="";
        jwtToken = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .signWith(KEY)
                .compact();
        return jwtToken;
    }

}
