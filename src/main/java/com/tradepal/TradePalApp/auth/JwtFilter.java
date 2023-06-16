package com.tradepal.TradePalApp.auth;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import static com.tradepal.TradePalApp.Generator.JWTGeneratorTokenImpl.KEY;

public class JwtFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader("authorization");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
        } else {
            try {
                if(authHeader == null || !authHeader.startsWith("Bearer ")){
                    throw new ServletException("An exception occurred");
                }
                final String token = authHeader.substring(7);
                Claims claims = Jwts
                        .parserBuilder()
                        .setSigningKey(KEY)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                String userRole = claims.get("role", String.class);
                request.setAttribute("claims", claims);
                request.setAttribute("user", servletRequest.getParameter("id"));
                request.setAttribute("userRole", userRole);
                chain.doFilter(request, response);
            } catch (SignatureException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");}

        }
    }
}
