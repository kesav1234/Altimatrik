package com.altimetrik.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altimetrik.challenge.model.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Override
    Optional<Customer> findById(Long UserId);

    boolean existsByEmail(String username);

    //User findByEmail(String username);

    Customer findByEmail(String email);



    @Query(value="Select * from user u where u.email=:email",nativeQuery = true)
    Optional<Customer> getUserByEmail(@Param("email") String email);



}
