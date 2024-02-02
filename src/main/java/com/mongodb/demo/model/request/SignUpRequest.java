package com.mongodb.demo.model.request;

import lombok.Data;

@Data
public class SignUpRequest {

    private String username;
    private String fullName;
    private String email;
    private String password;
    private String gender;
    private AddressRequest address;
}
