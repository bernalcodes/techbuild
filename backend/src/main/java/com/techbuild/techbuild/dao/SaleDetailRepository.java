package com.techbuild.techbuild.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techbuild.techbuild.domain.SaleDetail;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, String> {
	@Query("SELECT sd FROM SaleDetail sd WHERE sd.saleId = :saleId")
	public List<SaleDetail> findBySaleId(@Param("saleId") String saleId);
}
