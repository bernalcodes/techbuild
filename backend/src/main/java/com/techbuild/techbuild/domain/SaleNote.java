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
@Entity(name = "SaleNote")
@Table(name = "sale_note")
public class SaleNote {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false)
	private String id;

	@Column(name = "client_id")
	private String clientId;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "date")
	private String date;

	@Column(name = "payment_type_id")
	private String paymentTypeId;

	@Column(name = "payment_status")
	private String paymentStatus;

	@Column(name = "total")
	private String total;
}
