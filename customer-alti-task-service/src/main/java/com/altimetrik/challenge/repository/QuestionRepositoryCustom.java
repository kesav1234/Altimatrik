package com.altimetrik.challenge.repository;

import java.util.List;

import com.altimetrik.challenge.dto.PostDto;

public interface QuestionRepositoryCustom {

    List<PostDto> findByUserProfileId(Long UserProdId);
}
