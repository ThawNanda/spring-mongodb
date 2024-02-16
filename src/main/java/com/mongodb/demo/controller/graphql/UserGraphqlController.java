package com.mongodb.demo.controller.graphql;

import com.mongodb.demo.model.entity.Address;
import com.mongodb.demo.model.entity.User;
import com.mongodb.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserGraphqlController {

    private final UserService userService;

    @QueryMapping
    public List<User> findAll() {
        log.info("This is query-mapping");
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @QueryMapping
    public Optional<User> findById(@Argument String id) {
        return userService.findById(id);
    }

    @MutationMapping
    public User createUser(@Argument String username,
                           @Argument String fullName,
                           @Argument String email,
                           @Argument String password,
                           @Argument String gender,
                           @Argument Address address) {
        return userService.createUser(username,fullName,email,password,gender,address);
    }
}
