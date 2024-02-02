package com.mongodb.demo.service.impl;

import com.mongodb.demo.exception.ForbiddenException;
import com.mongodb.demo.model.entity.RoleName;
import com.mongodb.demo.model.request.LoginRequest;
import com.mongodb.demo.model.response.AuthenticationResponse;
import com.mongodb.demo.service.AuthService;
import com.mongodb.demo.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    public AuthenticationResponse authenticateUser(LoginRequest request) {

        Date expiredAt = new Date((new Date()).getTime() + 86400 * 1000);
        try {
            log.info("Start----");
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()));
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_USER.name()))) {
                String jwt = jwtService.generateToken(authentication);
                return new AuthenticationResponse(jwt, expiredAt.toString());
            }
        } catch (AuthenticationException e) {
            log.info(e.getMessage());
            throw new BadCredentialsException(e.getMessage());
        }
        throw new ForbiddenException("Authentication Failed!");
    }
}
