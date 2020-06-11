package com.altimetrik.challenge.service;

import org.springframework.http.ResponseEntity;

import com.altimetrik.challenge.model.Question;

public interface QuestionService {

    Question InsertPost(Question post,Long UserProfId);

    Question EditPost(Long postId,Question post);

    ResponseEntity<?> deletePost(Long PostId);
}
