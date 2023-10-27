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
import com.techbuild.techbuild.domain.Role;
import com.techbuild.techbuild.service.RoleService;

@RestController
@RequestMapping("roles")
public class RoleController {
	private final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private RoleService roleService;

	@GetMapping("/all")
	public ResponseEntity<String> getRoles() {
		logger.info("Entering getRoles()");
		try {
			List<Role> roles = roleService.getRoles();
			String json = mapper.valueToTree(roles).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{name}")
	public ResponseEntity<String> getRolesByName(@PathVariable String name) {
		logger.info("Entering getRolesByName(name)");
		try {
			List<Role> roles = roleService.getRolesByName(name);
			String json = mapper.valueToTree(roles).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getRoleById(@PathVariable String id) {
		logger.info("Entering getRoleById(id)");
		try {
			Role role = roleService.getRoleById(id);
			String json = mapper.valueToTree(role).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<String> createRole(@RequestBody Role role) {
		logger.info("Entering createRole(role)");
		try {
			Role createdRole = roleService.createRole(role);
			String json = mapper.valueToTree(createdRole).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.CREATED);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	public ResponseEntity<String> updateRole(@RequestBody Role role) {
		logger.info("Entering updateRole(role)");
		try {
			Role updatedRole = roleService.updateRole(role);
			String json = mapper.valueToTree(updatedRole).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.info(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deleteRole(@RequestBody Role role) {
		logger.info("Entering deleteRole(role)");
		try {
			boolean deleted = roleService.deleteRole(role);
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
	public ResponseEntity<String> deleteRoleById(@PathVariable String id) {
		logger.info("Entering deleteRoleById(id)");
		try {
			boolean deleted = roleService.deleteRoleById(id);
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
