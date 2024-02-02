package com.mongodb.demo.model.mapper;


import com.mongodb.demo.model.dto.AddressDto;
import com.mongodb.demo.model.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto toDto(Address address);
}
