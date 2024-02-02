package com.mongodb.demo.model.mapper;

import com.mongodb.demo.model.dto.UserDto;
import com.mongodb.demo.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);


}
