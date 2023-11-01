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
import com.techbuild.techbuild.domain.Employee;
import com.techbuild.techbuild.service.EmployeeService;

@RestController
@RequestMapping("employees")
public class EmployeeController {
	private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/all")
	public ResponseEntity<String> getEmployees() {
		logger.info("Entering getEmployees()");
		try {
			List<Employee> employees = employeeService.getEmployees();
			String json = mapper.valueToTree(employees).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{name}")
	public ResponseEntity<String> getEmployeesByName(@PathVariable String name) {
		logger.info("Entering getEmployeesByName(name)");
		try {
			List<Employee> employees = employeeService.getEmployeesByName(name);
			String json = mapper.valueToTree(employees).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{email}")
	public ResponseEntity<String> getEmployeeByEmail(@PathVariable String email) {
		logger.info("Entering getEmployeeByEmail(email)");
		try {
			List<Employee> employees = employeeService.getEmployeesByName(email);
			String json = mapper.valueToTree(employees).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
		logger.info("Entering createEmployee(employee)");
		try {
			Employee createdEmployee = employeeService.createEmployee(employee);
			String json = mapper.valueToTree(createdEmployee).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
		logger.info("Entering updateEmployee(employee)");
		try {
			Employee updatedEmployee = employeeService.updateEmployee(employee);
			String json = mapper.valueToTree(updatedEmployee).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.info(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deleteEmployee(@RequestBody Employee employee) {
		logger.info("Entering deleteEmployee(employee)");
		try {
			boolean deleted = employeeService.deleteEmployee(employee);
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
	public ResponseEntity<String> deleteEmployeeById(@PathVariable String id) {
		logger.info("Entering deleteEmployeeById(id)");
		try {
			boolean deleted = employeeService.deleteEmployeeById(id);
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
