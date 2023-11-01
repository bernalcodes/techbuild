package com.techbuild.techbuild.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techbuild.techbuild.domain.Survey;

public interface SurveyRepository extends JpaRepository<Survey, String> {

}
