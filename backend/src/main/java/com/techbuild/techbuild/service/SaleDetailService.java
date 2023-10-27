package com.techbuild.techbuild.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbuild.techbuild.dao.SaleDetailRepository;
import com.techbuild.techbuild.domain.SaleDetail;

@Service
public class SaleDetailService {
	@Autowired
	private SaleDetailRepository saleDetailRepository;

	// CREATE
	public SaleDetail createSaleDetail(SaleDetail saleDetail) {
		return saleDetailRepository.save(saleDetail);
	}

	// READ
	public List<SaleDetail> getSaleDetails() {
		return saleDetailRepository.findAll();
	}

	public SaleDetail getSaleDetailById(String id) {
		return saleDetailRepository.getReferenceById(UUID.fromString(id));
	}

	public List<SaleDetail> getSaleDetailsBySaleId(String saleId) {
		return saleDetailRepository.findBySaleId(UUID.fromString(saleId));
	}

	// UPDATE
	public SaleDetail updateSaleDetail(SaleDetail saleDetail) {
		return saleDetailRepository.saveAndFlush(saleDetail);
	}

	// DELETE
	public boolean deleteSaleDetail(SaleDetail saleDetail) {
		UUID id = UUID.fromString(saleDetail.getId());
		if (saleDetailRepository.existsById(id)) {
			saleDetailRepository.delete(saleDetail);
			return true;
		}
		return false;
	}

	public boolean deleteSaleDetailById(String saleDetailId) {
		UUID id = UUID.fromString(saleDetailId);
		if (saleDetailRepository.existsById(id)) {
			saleDetailRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
