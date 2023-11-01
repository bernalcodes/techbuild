package com.techbuild.techbuild.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techbuild.techbuild.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
	@Query("SELECT c FROM Category c WHERE c.name = :name")
	public List<Category> findByName(@Param("name") String name);
}
