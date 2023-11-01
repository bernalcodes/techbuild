package com.techbuild.techbuild.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbuild.techbuild.dao.SurveyRepository;
import com.techbuild.techbuild.domain.Survey;

@Service
public class SurveyService {
	@Autowired
	private SurveyRepository surveyRepository;

	// CREATE
	public Survey createSurvey(Survey survey) {
		return surveyRepository.save(survey);
	}

	// READ
	public List<Survey> getSurveys() {
		return surveyRepository.findAll();
	}

	public Survey getSurveyById(String id) {
		return surveyRepository.getReferenceById(id);
	}

	// UPDATE
	public Survey updateSurvey(Survey survey) {
		return surveyRepository.saveAndFlush(survey);
	}

	// DELETE
	public boolean deleteSurvey(Survey survey) {
		if (surveyRepository.existsById(survey.getId())) {
			surveyRepository.delete(survey);
			return true;
		}
		return false;
	}

	public boolean deleteSurveyById(String surveyId) {
		if (surveyRepository.existsById(surveyId)) {
			surveyRepository.deleteById(surveyId);
			return true;
		}
		return false;
	}
}
