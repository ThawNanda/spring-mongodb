package com.mongodb.demo.service;

import com.mongodb.demo.model.request.LoginRequest;
import com.mongodb.demo.model.response.AuthenticationResponse;

public interface AuthService {
    AuthenticationResponse authenticateUser(LoginRequest request);
}
