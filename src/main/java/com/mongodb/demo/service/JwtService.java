package com.mongodb.demo.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface JwtService {
    boolean isTokenValid(String jwt);

    String extractUsername(String jwt);

    Claims getClaims(String jwt);

    String generateToken(Authentication authentication);
}
