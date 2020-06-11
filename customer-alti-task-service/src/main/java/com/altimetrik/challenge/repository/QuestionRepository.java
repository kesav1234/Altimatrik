package com.altimetrik.challenge.repository;


import com.altimetrik.challenge.dto.PostDto;
import com.altimetrik.challenge.model.Question;
import com.altimetrik.challenge.model.CustProfile;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>,QuestionRepositoryCustom {








}

