package com.techbuild.techbuild.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techbuild.techbuild.domain.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
	@Query("SELECT r FROM Role r WHERE r.name = :name")
	public List<Role> findByName(@Param("name") String name);
}
