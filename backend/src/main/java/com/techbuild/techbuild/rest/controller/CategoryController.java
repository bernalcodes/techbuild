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
import com.techbuild.techbuild.domain.Category;
import com.techbuild.techbuild.service.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController {
	private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/all")
	public ResponseEntity<String> getCategories() {
		logger.info("Entering getCategories()");
		try {
			List<Category> categories = categoryService.getCategories();
			String json = mapper.valueToTree(categories).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{name}")
	public ResponseEntity<String> getCategoriesByName(@PathVariable String name) {
		logger.info("Entering getCategoriesByName(name)");
		try {
			List<Category> categories = categoryService.getCategoriesByName(name);
			String json = mapper.valueToTree(categories).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getCategory(@PathVariable(required = true) String id) {
		logger.info("Entering getCategory(id)");
		try {
			Category category = categoryService.getCategoryById(id);
			String json = mapper.valueToTree(category).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<String> createCategory(@RequestBody Category category) {
		logger.info("Entering createCategory(category)");
		try {
			Category newCategory = categoryService.createCategory(category);
			String json = mapper.valueToTree(newCategory).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.CREATED);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.info(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	public ResponseEntity<String> updateCategory(@RequestBody Category category) {
		logger.info("Entering updateCategory(category)");
		try {
			Category updatedCategory = categoryService.updateCategory(category);
			String json = mapper.valueToTree(updatedCategory).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deleteCategory(@RequestBody Category category) {
		logger.info("Entering deleteCategory(category)");
		try {
			if (categoryService.deleteCategory(category)) {
				String response = String.format("Category with ID %s deleted", category.getId());
				logger.info("Response: {}", response);
				return new ResponseEntity<String>(response, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Category not found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable String id) {
		logger.info("Entering deleteCategory(id)");
		try {
			if (categoryService.deleteCategoryById(id)) {
				String response = String.format("Category with ID %s deleted", id);
				logger.info("Response: {}", response);
				return new ResponseEntity<String>(response, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Category not found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
