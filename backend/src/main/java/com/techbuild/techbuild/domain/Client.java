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
@Entity(name = "Client")
@Table(name = "client")
public class Client {
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

	@Column(name = "rfc", nullable = false, length = 100)
	private String rfc;
}
