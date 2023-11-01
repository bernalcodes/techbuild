package com.techbuild.techbuild.service;

import java.util.List;

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
		return categoryRepository.getReferenceById(id);
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
		if (categoryRepository.existsById(category.getId())) {
			categoryRepository.delete(category);
			return true;
		}
		return false;
	}

	public boolean deleteCategoryById(String categoryId) {
		if (categoryRepository.existsById(categoryId)) {
			categoryRepository.deleteById(categoryId);
			return true;
		}
		return false;
	}
}
