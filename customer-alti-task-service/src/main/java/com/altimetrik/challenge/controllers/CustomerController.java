package com.altimetrik.challenge.controllers;


import com.altimetrik.challenge.RestResponse;
import com.altimetrik.challenge.dto.PostDto;
import com.altimetrik.challenge.exception.CustomException;
import com.altimetrik.challenge.exception.ResourceNotFoundException;
import com.altimetrik.challenge.model.Question;
import com.altimetrik.challenge.model.Customer;
import com.altimetrik.challenge.repository.QuestionRepository;
import com.altimetrik.challenge.repository.CustomerRepository;
import com.altimetrik.challenge.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value="challenge", description="Operations pertaining to customer)")
public class CustomerController {


    @Autowired
    private CustomerRepository userRepository;

    @Autowired
    private QuestionRepository postRepository;

    @Autowired
    private CustomerService userService;


    @PostMapping("/users/signup")
    @ApiOperation(value = "Signup for the user")
    public RestResponse createUser( @RequestBody Customer user){

        try{
           return RestResponse.createSuccessResponse(userService.insertUser(user));
        }
        catch(CustomException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }

   //    return RestResponse.createFailureResponse("User Already exists",400);

        }

    @GetMapping("/users/signin")
    @ApiOperation(value = "Signing in of the user")
    public RestResponse getUser( @RequestParam("email") String user){
        try{
           return RestResponse.createSuccessResponse(userService.fetchUserToken(user));

        }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }

    }


    @GetMapping("/users/getall")
    @ApiOperation(value = "Get all posts of the user")
    public List<PostDto> getAllPosts( @RequestParam("userProfId") Long userProfId){
        List<Question> posts= new ArrayList<>();

        return postRepository.findByUserProfileId(userProfId);



    }


}
