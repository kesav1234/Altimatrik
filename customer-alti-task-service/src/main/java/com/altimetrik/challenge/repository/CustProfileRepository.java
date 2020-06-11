package com.altimetrik.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.challenge.model.CustProfile;

import sun.awt.SunToolkit;

import java.util.Optional;

@Repository
public interface CustProfileRepository extends JpaRepository<CustProfile,Long> {
    @Override
    Optional<CustProfile> findById(Long userId);
}
