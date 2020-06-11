package com.altimetrik.challenge.controllers;



import com.altimetrik.challenge.RestResponse;
import com.altimetrik.challenge.exception.ResourceNotFoundException;
import com.altimetrik.challenge.model.Question;
import com.altimetrik.challenge.model.CustProfile;
import com.altimetrik.challenge.repository.QuestionRepository;
import com.altimetrik.challenge.repository.CustProfileRepository;
import com.altimetrik.challenge.service.QuestionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
@Api(value="challenge", description="Operations pertaining to questions(Creation,deletion and manupulation)")
@RestController
public class QuestionController {

    @Autowired
    QuestionRepository postRepository;

    @Autowired
    CustProfileRepository userProfileRepository;

    @Autowired
    QuestionService postService;

    @GetMapping("/posts")
    @ApiOperation(value = "Create a comment")
    public Page<Question> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @PostMapping("/posts/{userprofileid}")
    public RestResponse createPost( @RequestBody Question post, @PathVariable Long userprofileid) {
            try {
                postService.InsertPost(post, userprofileid);
                return RestResponse.createSuccessResponse(postRepository.save(post));
            }

        catch (ResourceNotFoundException e){
                return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }

    @PutMapping("/posts/{postId}")
    @ApiOperation(value = "Update a post based on the post id")
    public RestResponse<?> updatePost(@PathVariable Long postId, @RequestBody Question postRequest) {

        try{
            return RestResponse.createSuccessResponse(postService.EditPost(postId,postRequest));

        }catch (ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }


    @DeleteMapping("/posts/{postId}")
    @ApiOperation(value = "Delete a post based on the postid")
    public RestResponse<?> deletePostOfUser(@PathVariable Long postId) {


        try{return RestResponse.createSuccessResponse(postService.deletePost(postId)); }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }

}

