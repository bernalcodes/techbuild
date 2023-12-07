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
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "User")
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = true)
	private String id;

	@Column(name = "name", nullable = true)
	private String name;

	@Column(name = "last_name", nullable = true)
	private String lastName;

	@Column(name = "email", nullable = true)
	private String email;

	@Column(name = "password", nullable = true)
	private String password;

	@Column(name = "phone_number", nullable = true)
	private String phoneNumber;

	@Column(name = "address", nullable = true)
	private String address;

	@Column(name = "age", nullable = true)
	private int age;

	@Column(name = "rfc", nullable = true)
	private String rfc;

	@Column(name = "curp", nullable = true)
	private String curp;

	@Column(name = "nss", nullable = true)
	private String nss;

	@Column(name = "entry_form", nullable = true)
	private String entryForm;

	@Column(name = "role", nullable = true)
	private String role;
}
