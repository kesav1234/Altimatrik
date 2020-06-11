package com.altimetrik.challenge.service;

import com.altimetrik.challenge.exception.CustomException;
import com.altimetrik.challenge.exception.ResourceNotFoundException;
import com.altimetrik.challenge.model.Customer;
import com.altimetrik.challenge.repository.CustomerRepository;
import com.altimetrik.challenge.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public String insertUser(Customer user){
        boolean flag;

        flag=alreadyRegistered(user.getEmail());
        if(flag!=true) {
            user.getUserProfile().setUser(user);
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
        }
        else
        {
            throw new CustomException("User is already registered", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public String fetchUserToken(String email){
        Customer user;
//        user=userRepository.findByEmail(email);
//        return user;
        boolean flag;
        flag=alreadyRegistered(email);
        if(flag) {
            user=getUser(email);
            return jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
        }
        else{
            throw new ResourceNotFoundException("User is not registered");
        }


    }

    public Customer getUser(String email) {

      Customer user;
      user= userRepository.findByEmail(email);
      return user;
    }

    @Override
    public boolean alreadyRegistered(String email){

        return userRepository.existsByEmail(email);


    }
}
