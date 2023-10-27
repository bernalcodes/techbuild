package com.techbuild.techbuild.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbuild.techbuild.dao.EmployeeRepository;
import com.techbuild.techbuild.domain.Employee;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	// CREATE
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	// READ
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	public List<Employee> getEmployeesByName(String name) {
		return employeeRepository.findByName(name);
	}

	public Employee getEmployeeById(String id) {
		return employeeRepository.getReferenceById(UUID.fromString(id));
	}

	// UPDATE
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.saveAndFlush(employee);
	}

	// DELETE
	public boolean deleteEmployee(Employee employee) {
		UUID id = UUID.fromString(employee.getId());
		if (employeeRepository.existsById(id)) {
			employeeRepository.delete(employee);
			return true;
		}
		return false;
	}

	public boolean deleteEmployeeById(String employeeId) {
		UUID id = UUID.fromString(employeeId);
		if (employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
