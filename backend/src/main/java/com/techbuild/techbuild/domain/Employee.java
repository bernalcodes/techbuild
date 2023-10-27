package com.techbuild.techbuild.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "Employee")
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false)
	private String id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "last_name", nullable = false, length = 100)
	private String lastName;

	@Column(name = "email", nullable = false, length = 100)
	private String email;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Column(name = "phone_number", nullable = false, length = 100)
	private String phoneNumber;

	@Column(name = "address", nullable = false, length = 100)
	private String address;

	@Column(name = "age", nullable = false, length = 100)
	private int age;

	@Column(name = "rfc", nullable = false, length = 100)
	private String rfc;

	@Column(name = "curp", nullable = false, length = 100)
	private String curp;

	@Column(name = "nss", nullable = false, length = 100)
	private String nss;

	@Column(name = "entry_form", nullable = false, length = 100)
	private String entryForm;

	@Column(name = "role_id", nullable = false, length = 100)
	private String roleId;
}
