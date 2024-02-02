package com.mongodb.demo.model.dto;

import com.mongodb.demo.model.entity.Gender;
import lombok.Data;

@Data
public class UserDto {

    private String id;
    private String username;
    private String fullName;
    private String email;
    private Gender gender;

}
