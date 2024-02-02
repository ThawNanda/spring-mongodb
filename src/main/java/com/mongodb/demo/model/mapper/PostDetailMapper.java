package com.mongodb.demo.model.mapper;

import com.mongodb.demo.model.dto.CommentDto;
import com.mongodb.demo.model.dto.UserDto;
import com.mongodb.demo.model.entity.Post;
import com.mongodb.demo.model.response.CommentResponse;
import com.mongodb.demo.model.response.PostResponse;
import com.mongodb.demo.model.response.UserResponse;
import com.mongodb.demo.service.CommentService;
import com.mongodb.demo.service.UserService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PostDetailMapper {

    @Autowired
    protected UserService userService;

    @Autowired
    protected CommentService commentService;


    public PostResponse toPostDetailResponse(Post post) {
        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setTitle(post.getTitle());
        response.setContent(response.getContent());
        List<CommentResponse> comments = new ArrayList<>();
        List<CommentDto> commentDtos = commentService.getComments(post.getId());
        commentDtos.forEach(comment -> {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setId(comment.getId());
            commentResponse.setText(comment.getText());
            UserDto userDto = userService.getUserById(comment.getUserId());
            UserResponse userResponse = new UserResponse();
            userResponse.setId(userDto.getId());
            userResponse.setUsername(userDto.getUsername());
            commentResponse.setUser(userResponse);
            commentResponse.setCreatedDate(comment.getCreatedDate());
            comments.add(commentResponse);
        });
        response.setComments(comments);
        return response;
    }
}
