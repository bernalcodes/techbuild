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
@Entity(name = "Category")
@Table(name = "category")
public class Category {
	/**
	 * CREATE TABLE categories (
	 * id VARCHAR(255) PRIMARY KEY NOT NULL,
	 * name VARCHAR(255) NOT NULL,
	 * description VARCHAR(255) NOT NULL,
	 * status VARCHAR(255) NOT NULL
	 * );
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false)
	private String id;

	@Column(unique = true, nullable = false, length = 100, name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private String status;
}
