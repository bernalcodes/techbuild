package com.techbuild.techbuild.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techbuild.techbuild.domain.Survey;
import com.techbuild.techbuild.service.SurveyService;

@RestController
@RequestMapping("surveys")
public class SurveyController {
	private final Logger logger = LoggerFactory.getLogger(SurveyController.class);

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private SurveyService surveyService;

	@GetMapping("/all")
	public ResponseEntity<String> getSurveys() {
		logger.info("Entering getSurveys()");
		try {
			List<Survey> surveys = surveyService.getSurveys();
			String json = mapper.valueToTree(surveys).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getSurvey(@PathVariable String id) {
		logger.info("Entering getSurveyById(id)");
		try {
			Survey survey = surveyService.getSurveyById(id);
			String json = mapper.valueToTree(survey).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<String> createSurvey(@RequestBody Survey survey) {
		logger.info("Entering createSurvey(survey)");
		try {
			Survey createdSurvey = surveyService.createSurvey(survey);
			String json = mapper.valueToTree(createdSurvey).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.CREATED);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	public ResponseEntity<String> updateSurvey(@RequestBody Survey survey) {
		logger.info("Entering updateSurvey(survey)");
		try {
			Survey updatedSurvey = surveyService.updateSurvey(survey);
			String json = mapper.valueToTree(updatedSurvey).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deleteSurvey(@RequestBody Survey survey) {
		logger.info("Entering deleteSurvey(survey)");
		try {
			boolean deleted = surveyService.deleteSurvey(survey);
			String json = mapper.valueToTree(deleted).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.info(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteSurvey(@PathVariable String id) {
		logger.info("Entering deleteSurvey(id)");
		try {
			boolean deleted = surveyService.deleteSurveyById(id);
			String json = mapper.valueToTree(deleted).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.info(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
