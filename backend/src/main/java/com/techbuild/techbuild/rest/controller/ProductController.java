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
import com.techbuild.techbuild.domain.Product;
import com.techbuild.techbuild.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private ProductService productService;

	@GetMapping("/all")
	public ResponseEntity<String> getAllProducts() {
		logger.info("Entering getProducts()");
		try {
			List<Product> products = productService.getProducts();
			String json = mapper.valueToTree(products).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{name}")
	public ResponseEntity<String> getProductsByName(@PathVariable String name) {
		logger.info("Entering getProductsByName(name)");
		try {
			List<Product> products = productService.getProductsByName(name);
			String json = mapper.valueToTree(products).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{category}")
	public ResponseEntity<String> getProductsByCategory(@PathVariable String category) {
		logger.info("Entering getProductsByCategory(category)");
		try {
			List<Product> products = productService.getProductsByCategory(category);
			String json = mapper.valueToTree(products).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getProductById(@PathVariable(required = true) String id) {
		logger.info("Entering getProductById(id)");
		try {
			Product product = productService.getProductById(id);
			String json = mapper.valueToTree(product).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<String> createProduct(@RequestBody(required = true) Product product) {
		logger.info("Entering createProduct(product)");
		try {
			Product newProduct = productService.createProduct(product);
			String json = mapper.valueToTree(newProduct).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.CREATED);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.info(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	public ResponseEntity<String> updateProduct(@RequestBody(required = true) Product product) {
		logger.info("Entering updateProduct(product)");
		try {
			Product updatedProduct = productService.updateProduct(product);
			String json = mapper.valueToTree(updatedProduct).toString();
			logger.info("ResponseBody: {}", json);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deleteProduct(@RequestBody(required = true) Product product) {
		logger.info("Entering deleteProduct(product)");
		try {
			if (productService.deleteProduct(product)) {
				String response = String.format("Product with ID %s deleted", product.getId());
				logger.info("Response: {}", response);
				return new ResponseEntity<String>(response, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable(required = true) String id) {
		logger.info("Entering deleteProductById(id)");
		try {
			if (productService.deleteProductById(id)) {
				String response = String.format("Product with ID %s deleted", id);
				logger.info("Response: {}", response);
				return new ResponseEntity<String>(response, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			String errorMsg = String.format("ERROR: {}", e.getMessage());
			logger.error(errorMsg);
			return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
