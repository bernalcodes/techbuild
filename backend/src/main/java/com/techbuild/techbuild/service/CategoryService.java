package com.techbuild.techbuild.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbuild.techbuild.dao.CategoryRepository;
import com.techbuild.techbuild.domain.Category;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	// CREATE
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	// READ
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

	public Category getCategoryById(String id) {
		return categoryRepository.getReferenceById(UUID.fromString(id));
	}

	public List<Category> getCategoriesByName(String name) {
		return categoryRepository.findByName(name);
	}

	// UPDATE
	public Category updateCategory(Category category) {
		return categoryRepository.saveAndFlush(category);
	}

	// DELETE
	public boolean deleteCategory(Category category) {
		UUID id = UUID.fromString(category.getId());
		if (categoryRepository.existsById(id)) {
			categoryRepository.delete(category);
			return true;
		}
		return false;
	}

	public boolean deleteCategoryById(String categoryId) {
		UUID id = UUID.fromString(categoryId);
		if (categoryRepository.existsById(id)) {
			categoryRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
