package com.mongodb.demo.service.impl;

import com.mongodb.demo.model.dto.CommentDto;
import com.mongodb.demo.model.entity.Comment;
import com.mongodb.demo.model.entity.User;
import com.mongodb.demo.model.mapper.CommentMapper;
import com.mongodb.demo.model.request.CommentRequest;
import com.mongodb.demo.repository.CommentRepository;
import com.mongodb.demo.repository.UserRepository;
import com.mongodb.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    private final UserRepository userRepository;

    @Override
    public CommentDto addComment(String email, CommentRequest request) {
        User user = userRepository.findByEmail(email).orElseThrow();
        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setPostId(request.getPostId());
        comment.setUserId(user.getId());
        Instant instant = Instant.now();
        comment.setCreatedDate(Date.from(instant));
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toCommentDto(savedComment);
    }

    @Override
    public List<CommentDto> getComments(String postId) {
        List<Comment>comments = commentRepository.getCommentsByPostId(postId);
        return comments.stream().map(commentMapper::toCommentDto).toList();
    }
}
