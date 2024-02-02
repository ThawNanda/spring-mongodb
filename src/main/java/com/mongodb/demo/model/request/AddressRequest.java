package com.mongodb.demo.model.request;

import lombok.Data;

@Data
public class AddressRequest {

    private String city;
    private String country;
    private String postCode;
}
