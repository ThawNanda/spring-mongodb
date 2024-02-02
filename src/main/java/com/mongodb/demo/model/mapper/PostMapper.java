package com.mongodb.demo.model.mapper;

import com.mongodb.demo.model.dto.PostDto;
import com.mongodb.demo.model.entity.Post;
import com.mongodb.demo.model.response.PostResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper{

    PostDto toPostDto(Post post);

}
