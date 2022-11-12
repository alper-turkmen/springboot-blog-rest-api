package com.springboot.ecommerceproject.service;

import com.springboot.ecommerceproject.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long productId, CommentDto commentDto);

    List<CommentDto> getCommentsByproductId(long productId);

    CommentDto getCommentById(long productId, long commentId);

    CommentDto updateComment(long productId, long commentId, CommentDto commentRequest);

    void deleteComment(long productId, long commentId);

}
