package com.mongodb.demo.model.mapper;

import com.mongodb.demo.model.dto.CommentDto;
import com.mongodb.demo.model.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto toCommentDto(Comment comment);
}
