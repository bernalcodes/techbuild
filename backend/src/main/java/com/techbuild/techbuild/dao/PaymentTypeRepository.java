package com.techbuild.techbuild.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techbuild.techbuild.domain.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, String> {
	@Query("SELECT p FROM PaymentType p WHERE p.name = :name")
	public List<PaymentType> findByName(@Param("name") String name);
}
