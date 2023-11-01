package com.techbuild.techbuild.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techbuild.techbuild.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	@Query("SELECT e FROM Employee e WHERE e.name = :name")
	public List<Employee> findByName(@Param("name") String name);
}
