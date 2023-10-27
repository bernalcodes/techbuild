package com.techbuild.techbuild.service;

import java.util.List;
import java.util.UUID;

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
		return surveyRepository.getReferenceById(UUID.fromString(id));
	}

	// UPDATE
	public Survey updateSurvey(Survey survey) {
		return surveyRepository.saveAndFlush(survey);
	}

	// DELETE
	public boolean deleteSurvey(Survey survey) {
		UUID id = UUID.fromString(survey.getId());
		if (surveyRepository.existsById(id)) {
			surveyRepository.delete(survey);
			return true;
		}
		return false;
	}

	public boolean deleteSurveyById(String surveyId) {
		UUID id = UUID.fromString(surveyId);
		if (surveyRepository.existsById(id)) {
			surveyRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
