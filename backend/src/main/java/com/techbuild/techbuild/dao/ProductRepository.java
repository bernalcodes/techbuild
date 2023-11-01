package com.techbuild.techbuild.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techbuild.techbuild.domain.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	@Query("SELECT p FROM Product p WHERE p.name LIKE :name")
	public List<Product> findByName(@Param("name") String name);
	
	@Query("SELECT p FROM Product p WHERE p.category LIKE :category")
	public List<Product> findByCategory(@Param("category") String category);
}
