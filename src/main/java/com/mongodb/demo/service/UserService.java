package com.mongodb.demo.service;

import com.mongodb.demo.model.dto.UserDto;
import com.mongodb.demo.model.request.SignUpRequest;

import java.util.List;

public interface UserService {
    void addUser();

    void signUpUser(SignUpRequest request);

    UserDto getUserByEmail(String username);

    UserDto getUserById(String userId);

    List<UserDto> getUsersByCountry(String country);
}
