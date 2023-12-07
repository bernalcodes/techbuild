package com.techbuild.techbuild.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techbuild.techbuild.domain.User;
import com.techbuild.techbuild.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("users")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public ResponseEntity<String> getUsers() {
		logger.info("Entering getUsers()");
		try {
			List<User> employees = userService.getUsers();
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
	public ResponseEntity<String> getUsersByName(@PathVariable String name) {
		logger.info("Entering getUsersByName(name)");
		try {
			List<User> employees = userService.getUsersByName(name);
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
	public ResponseEntity<String> getUserByEmail(@PathVariable String email) {
		logger.info("Entering getUserByEmail(email)");
		try {
			List<User> employees = userService.getUsersByName(email);
			String json = mapper.valueToTree(employees).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login")
	public String login(User user, HttpSession session) {
		logger.info("Entering login()");
		logger.info("User: {}", user.toString());
		try {
			User foundUser = userService.getUserByEmail(user.getEmail());
			logger.info("Found user: {}", foundUser.toString());
			session.setAttribute("user", foundUser);
			return "redirect:/?login_success=true";
		} catch (Exception e) {
			logger.error("ERROR: {}", e.getMessage());
			return "redirect:/login?login_error=true";
		}
	}

	@PostMapping("/create")
	public String createUser(User user, Model model) {
		logger.info("Entering createUser(user)");
		logger.info("User: {}", user.toString());
		try {
			user.setRole("USER");
			User createdUser = userService.createUser(user);
			logger.info("User created: {}", createdUser.toString());
			model.addAttribute("user", createdUser);
			return "redirect:/login?signup_success=true";
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return "redirect:/signup?signup_error=true";
		}
	}

	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody User User) {
		logger.info("Entering updateUser(User)");
		try {
			User updatedUser = userService.updateUser(User);
			String json = mapper.valueToTree(updatedUser).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.info(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deleteUser(@RequestBody User User) {
		logger.info("Entering deleteUser(User)");
		try {
			boolean deleted = userService.deleteUser(User);
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
	public ResponseEntity<String> deleteUserById(@PathVariable String id) {
		logger.info("Entering deleteUserById(id)");
		try {
			boolean deleted = userService.deleteUserById(id);
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
