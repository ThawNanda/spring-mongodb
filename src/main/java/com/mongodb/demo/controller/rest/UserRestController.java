package com.mongodb.demo.controller.rest;

import com.mongodb.demo.model.dto.UserDto;
import com.mongodb.demo.security.CurrentUser;
import com.mongodb.demo.security.UserPrincipal;
import com.mongodb.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(){
        userService.addUser();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/me")
    public UserDto getCurrentUser(@CurrentUser UserPrincipal currentUser){
        return userService.getUserByEmail(currentUser.getUsername());
    }

    @GetMapping("/country")
    public List<UserDto> getUsers(@RequestParam String country){
        return userService.getUsersByCountry(country);
    }
}
