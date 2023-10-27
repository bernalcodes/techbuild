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
import com.techbuild.techbuild.domain.PaymentType;
import com.techbuild.techbuild.service.PaymentTypeService;

@RestController
@RequestMapping("payment-types")
public class PaymentTypeController {
	private final Logger logger = LoggerFactory.getLogger(PaymentTypeController.class);

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private PaymentTypeService paymentTypeService;

	@GetMapping("/all")
	public ResponseEntity<String> getPaymentTypes() {
		logger.info("Entering getPaymentTypes()");
		try {
			List<PaymentType> paymentTypes = paymentTypeService.getPaymentTypes();
			String json = mapper.valueToTree(paymentTypes).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{name}")
	public ResponseEntity<String> getPaymentTypesByName(@PathVariable String name) {
		logger.info("Entering getPaymentTypesByName(name)");
		try {
			List<PaymentType> paymentTypes = paymentTypeService.getPaymentTypesByName(name);
			String json = mapper.valueToTree(paymentTypes).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getPaymentTypeById(@PathVariable String id) {
		logger.info("Entering getPaymentTypeById(id)");
		try {
			PaymentType paymentType = paymentTypeService.getPaymentTypeById(id);
			String json = mapper.valueToTree(paymentType).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<String> createPaymentType(@RequestBody PaymentType paymentType) {
		logger.info("Entering createPaymentType(paymentType)");
		try {
			PaymentType newPaymentType = paymentTypeService.createPaymentType(paymentType);
			String json = mapper.valueToTree(newPaymentType).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.CREATED);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	public ResponseEntity<String> updatePaymentType(@RequestBody PaymentType paymentType) {
		logger.info("Entering updatePaymentType(paymentType)");
		try {
			PaymentType updatedPaymentType = paymentTypeService.updatePaymentType(paymentType);
			String json = mapper.valueToTree(updatedPaymentType).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.info(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deletePaymentType(@RequestBody PaymentType paymentType) {
		logger.info("Entering deletePaymentType(paymentType)");
		try {
			boolean deleted = paymentTypeService.deletePaymentType(paymentType);
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
	public ResponseEntity<String> deletePaymentTypeById(@PathVariable String id) {
		logger.info("Entering deletePaymentTypeById(id)");
		try {
			boolean deleted = paymentTypeService.deletePaymentTypeById(id);
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
