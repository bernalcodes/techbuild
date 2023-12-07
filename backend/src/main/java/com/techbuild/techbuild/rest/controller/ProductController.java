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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techbuild.techbuild.domain.Category;
import com.techbuild.techbuild.domain.Product;
import com.techbuild.techbuild.service.CategoryService;
import com.techbuild.techbuild.service.FileUploadService;
import com.techbuild.techbuild.service.ProductService;

@Controller
@RequestMapping("products")
public class ProductController {
	private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private FileUploadService fileUploadService;

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

	@GetMapping("/id/{id}")
	public String getProductById(Model model, @PathVariable(required = true) String id) {
		logger.info("Entering getProductById(id)");
		try {
			Product product = productService.getProductById(id);
			logger.info("Product: {}", product.toString());
			Category category = categoryService.getCategoryById(product.getCategory());
			logger.info("Category: {}", category.toString());
			model.addAttribute("product", product);
			model.addAttribute("category", category.getName());
			return "all/product/read";
		} catch (Exception e) {
			logger.error("ERROR: {}", e.getMessage());
			return "redirect:/?error";
		}
	}

	@GetMapping("/create")
	public String createProduct(Model model) {
		logger.info("Entering createProduct(model)");
		List<Category> categories = categoryService.getCategories();
		categories.forEach(category -> logger.info("Category: {}", category));
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categories);
		return "admin/product/create";
	}

	@PostMapping
	public String createProduct(Model model, Product product, @RequestParam("img") MultipartFile file) {
		logger.info("Entering createProduct(product, file)");
		logger.info("Product: {}", product.toString());
		
		try {
			List<Product> productList = productService.getProductsByName(product.getName());
			if (productList.size() > 0) {
				model.addAttribute("product", product);
				model.addAttribute("error", "El producto ya existe");
				return "redirect:/productos/create?error";
			} else {
				String pictureName = fileUploadService.saveImage(file);
				product.setPicture(pictureName);
				Product newProduct = productService.createProduct(product);
				model.addAttribute("success", "Producto creado exitosamente");
				model.addAttribute("product", newProduct);
				return "redirect:/?success";
			}
		} catch (Exception e) {
			logger.info("ERROR: {}", e.getMessage());
			model.addAttribute("error", "Ocurrió un error creando el producto");
			return "redirect:/productos/create?error";
		}
	}

	@GetMapping("/edit/{id}")
	public String editProduct(Model model, @PathVariable(required = true) String id) {
		logger.info("Entering editProduct(model, id)");
		try {
			Product editProduct = productService.getProductById(id);
			logger.info("Product: {}", editProduct.toString());
			model.addAttribute("product", editProduct);
			return "admin/product/edit";
		} catch (Exception e) {
			logger.error("ERROR: {}", e.getMessage());
			return "redirect:/";
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
