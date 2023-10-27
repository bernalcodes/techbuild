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
import com.techbuild.techbuild.domain.SaleNote;
import com.techbuild.techbuild.service.SaleNoteService;

@RestController
@RequestMapping("sale-notes")
public class SaleNoteController {
	private final Logger logger = LoggerFactory.getLogger(SaleNoteController.class);

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private SaleNoteService saleNoteService;

	@GetMapping("/all")
	public ResponseEntity<String> getSaleNotes() {
		logger.info("Entering getSaleNotes()");
		try {
			List<SaleNote> saleNotes = saleNoteService.getSaleNotes();
			String json = mapper.valueToTree(saleNotes).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getSaleNoteById(@PathVariable String id) {
		logger.info("Entering getSaleNoteById(id)");
		try {
			SaleNote saleNote = saleNoteService.getSaleNoteById(id);
			String json = mapper.valueToTree(saleNote).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<String> createSaleNote(@RequestBody SaleNote saleNote) {
		logger.info("Entering createSaleNote()");
		try {
			SaleNote createdSaleNote = saleNoteService.createSaleNote(saleNote);
			String json = mapper.valueToTree(createdSaleNote).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.CREATED);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	public ResponseEntity<String> updateSaleNoteById(@RequestBody SaleNote saleNote) {
		logger.info("Entering updateSaleNoteById()");
		try {
			SaleNote updatedSaleNote = saleNoteService.updateSaleNoteById(saleNote);
			String json = mapper.valueToTree(updatedSaleNote).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deleteSaleNote(@RequestBody SaleNote saleNote) {
		logger.info("Entering deleteSaleNoteById()");
		try {
			boolean isDeleted = saleNoteService.deleteSaleNote(saleNote);
			String json = mapper.valueToTree(isDeleted).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.info(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteSaleNote(@PathVariable String id) {
		logger.info("Entering deleteSaleNoteById(id)");
		try {
			boolean isDeleted = saleNoteService.deleteSaleNoteById(id);
			String json = mapper.valueToTree(isDeleted).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.info(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
