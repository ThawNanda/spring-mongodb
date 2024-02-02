package com.mongodb.demo.model.response;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String type = "Bearer";
    private String token;
    private String expiredAt;

    public AuthenticationResponse(String token, String expiredAt) {
        this.token = token;
        this.expiredAt = expiredAt;
    }
}
