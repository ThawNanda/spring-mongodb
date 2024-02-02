package com.mongodb.demo.controller;

import com.mongodb.demo.model.request.LoginRequest;
import com.mongodb.demo.model.request.SignUpRequest;
import com.mongodb.demo.model.response.ApiResponse;
import com.mongodb.demo.model.response.AuthenticationResponse;
import com.mongodb.demo.service.AuthService;
import com.mongodb.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupAccount(@RequestBody SignUpRequest request) {
        userService.signUpUser(request);
        return new ResponseEntity<>(new ApiResponse(true, "Success"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAccount(@RequestBody LoginRequest request) {
        AuthenticationResponse response = authService.authenticateUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
