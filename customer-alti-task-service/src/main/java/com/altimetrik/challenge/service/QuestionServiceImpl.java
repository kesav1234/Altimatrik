package com.altimetrik.challenge.service;

import com.altimetrik.challenge.RestResponse;
import com.altimetrik.challenge.exception.ResourceNotFoundException;
import com.altimetrik.challenge.model.Question;
import com.altimetrik.challenge.model.CustProfile;
import com.altimetrik.challenge.repository.QuestionRepository;
import com.altimetrik.challenge.repository.CustProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    CustProfileRepository userProfileRepository;

    @Autowired
    QuestionRepository postRepository;

    @Override
    public Question InsertPost(Question post,Long UserProfId){

        Optional<CustProfile> userProfile = userProfileRepository.findById(UserProfId);
        if(userProfile != null) {
            post.setUserProfile(userProfile.get());
            return post;
        }
        else{throw new ResourceNotFoundException("User Profile Is Not Found");
        }

       // return userProfileRepository.findById(UserProfId).map(post.s).orElseThrow(()->new ResourceNotFoundException("UserProfile Not found"));

    }

    @Override
    public Question EditPost(Long postId,Question post){

        return postRepository.findById(postId).map(Updatedpost -> {
            Updatedpost.setTitle(post.getTitle());
            Updatedpost.setDescription(post.getDescription());
            Updatedpost.setContent(post.getContent());
            return postRepository.save(Updatedpost);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));

    }

    @Override
    public ResponseEntity<?> deletePost(Long postId){
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
}
