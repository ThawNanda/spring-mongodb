package com.mongodb.demo.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String fullName;
    private String password;

    @Indexed(unique = true)
    private String email;

    private Gender gender;
    private Address address;
    private List<RoleName> roles;
    private LocalDateTime createdAt;

}
