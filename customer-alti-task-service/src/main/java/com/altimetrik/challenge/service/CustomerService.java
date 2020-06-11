package com.altimetrik.challenge.service;

import java.util.Optional;

import com.altimetrik.challenge.model.Customer;

public interface CustomerService {

    String insertUser(Customer user);
    String fetchUserToken(String user);
    boolean alreadyRegistered(String email);

}
