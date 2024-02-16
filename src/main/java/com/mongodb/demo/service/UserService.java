package com.mongodb.demo.service;

import com.mongodb.demo.model.dto.UserDto;
import com.mongodb.demo.model.entity.Address;
import com.mongodb.demo.model.entity.User;
import com.mongodb.demo.model.request.SignUpRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser();

    void signUpUser(SignUpRequest request);

    UserDto getUserByEmail(String username);

    UserDto getUserById(String userId);

    List<UserDto> getUsersByCountry(String country);

    List<User> findAll();

    Optional<User> findById(String id);

    User createUser(String username, String fullName, String email, String password, String gender, Address address);
}
