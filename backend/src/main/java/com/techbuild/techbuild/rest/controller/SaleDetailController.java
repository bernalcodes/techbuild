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
import com.techbuild.techbuild.domain.SaleDetail;
import com.techbuild.techbuild.service.SaleDetailService;

@RestController
@RequestMapping("sale-details")
public class SaleDetailController {
	private final Logger logger = LoggerFactory.getLogger(SaleDetailController.class);

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private SaleDetailService saleDetailService;

	@GetMapping("/all")
	public ResponseEntity<String> getSaleDetails() {
		logger.info("Entering getSaleDetails()");
		try {
			List<SaleDetail> saleDetails = saleDetailService.getSaleDetails();
			String json = mapper.valueToTree(saleDetails).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getSaleDetailById(@PathVariable String id) {
		logger.info("Entering getSaleDetailById(id)");
		try {
			SaleDetail saleDetail = saleDetailService.getSaleDetailById(id);
			String json = mapper.valueToTree(saleDetail).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/sale/{saleId}")
	public ResponseEntity<String> getSaleDetailsBySaleId(@PathVariable String saleId) {
		logger.info("Entering getSaleDetailsBySaleId(saleId)");
		try {
			List<SaleDetail> saleDetails = saleDetailService.getSaleDetailsBySaleId(saleId);
			String json = mapper.valueToTree(saleDetails).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<String> createSaleDetail(@RequestBody SaleDetail saleDetail) {
		logger.info("Entering createSaleDetail(saleDetail)");
		try {
			SaleDetail newSaleDetail = saleDetailService.createSaleDetail(saleDetail);
			String json = mapper.valueToTree(newSaleDetail).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.CREATED);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	public ResponseEntity<String> updateSaleDetail(@RequestBody SaleDetail saleDetail) {
		logger.info("Entering updateSaleDetail(saleDetail)");
		try {
			SaleDetail updatedSaleDetail = saleDetailService.updateSaleDetail(saleDetail);
			String json = mapper.valueToTree(updatedSaleDetail).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.info(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deleteSaleDetail(@RequestBody SaleDetail saleDetail) {
		logger.info("Entering deleteSaleDetail(saleDetail)");
		try {
			boolean deleted = saleDetailService.deleteSaleDetail(saleDetail);
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
	public ResponseEntity<String> deleteSaleDetailById(@PathVariable String id) {
		logger.info("Entering deleteSaleDetailById(id)");
		try {
			boolean deleted = saleDetailService.deleteSaleDetailById(id);
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
