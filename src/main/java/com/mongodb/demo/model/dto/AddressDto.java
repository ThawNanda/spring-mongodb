package com.mongodb.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    private String city;
    private String country;
    private String postCode;
}
