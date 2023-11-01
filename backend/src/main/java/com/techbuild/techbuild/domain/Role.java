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
@Entity(name = "Role")
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false)
	private String id;

	@Column(unique = true, nullable = false, length = 100, name = "name")
	private String name;

	@Column(name = "description")
	private String description;
}
