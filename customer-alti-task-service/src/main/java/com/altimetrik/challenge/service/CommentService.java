package com.altimetrik.challenge.service;

import org.springframework.http.ResponseEntity;

import com.altimetrik.challenge.model.Comment;

public interface CommentService {
    Comment saveComment(Long posId,Comment comment);
    Comment editComment(Long commentId,Comment comment);
    ResponseEntity<?> deleteComment(Long commentId);
}
