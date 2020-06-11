package com.altimetrik.challenge.controllers;


import com.altimetrik.challenge.RestResponse;
import com.altimetrik.challenge.exception.ResourceNotFoundException;
import com.altimetrik.challenge.model.Comment;
import com.altimetrik.challenge.model.CustProfile;
import com.altimetrik.challenge.repository.CommentRepository;
import com.altimetrik.challenge.repository.QuestionRepository;
import com.altimetrik.challenge.repository.CustProfileRepository;
import com.altimetrik.challenge.service.CommentService;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Api(value="challenge", description="Operations pertaining to answers over a question")
public class CommentController {



    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private QuestionRepository postRepository;

    @Autowired
    private CustProfileRepository userProfileRepository;

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "Displays all commentes which is related to the question")
    public Page<Comment> getAllCommentsByPostId(@PathVariable (value = "postId") Long postId,
                                                Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }

    @PostMapping("/Questions/{postId}/{userProfileId}/comments")
    @ApiOperation(value = "Create a comment")
    public RestResponse<?> createComment(@PathVariable (value = "postId") Long postId,
                                      @RequestBody Comment comment,@PathVariable (value = "userProfileId") Long userProfileId) {

        Optional<CustProfile> userProfile=userProfileRepository.findById(userProfileId);
        comment.setUserProfile(userProfile.get());
        try {
            return RestResponse.createSuccessResponse(commentService.saveComment(postId, comment));
        }
        catch(ResourceNotFoundException e)
        {
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }

    }

    @PutMapping("/questions/{postId}/comments/{commentId}")
    @ApiOperation(value = "Update a comment")
    public RestResponse<?> updateComment(@PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @RequestBody Comment commentRequest) {
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
      try{
          return RestResponse.createSuccessResponse(commentService.editComment(commentId,commentRequest));
      }
      catch(ResourceNotFoundException e)
        {
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }

    }

    @DeleteMapping("/questions/{postId}/comments/{commentId}")
    @ApiOperation(value = "Delete a comment")
    public RestResponse<?> deleteComment(@PathVariable (value = "postId") Long postId,
                                           @PathVariable (value = "commentId") Long commentId) {
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        try{
            return RestResponse.createSuccessResponse(commentService.deleteComment(commentId));
        }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }
}

