package com.techbuild.techbuild.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbuild.techbuild.dao.ProductRepository;
import com.techbuild.techbuild.domain.Product;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	// CREATE
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	// READ
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	public List<Product> getProductsByName(String name) {
		return productRepository.findByName(name);
	}

	public List<Product> getProductsByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	public Product getProductById(String id) {
		return productRepository.getReferenceById(UUID.fromString(id));
	}

	// UPDATE
	public Product updateProduct(Product product) {
		return productRepository.saveAndFlush(product);
	}

	// DELETE
	public boolean deleteProduct(Product product) {
		UUID id = UUID.fromString(product.getId());
		if (productRepository.existsById(id)) {
			productRepository.delete(product);
			return true;
		}
		return false;
	}

	public boolean deleteProductById(String productId) {
		UUID id = UUID.fromString(productId);
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
