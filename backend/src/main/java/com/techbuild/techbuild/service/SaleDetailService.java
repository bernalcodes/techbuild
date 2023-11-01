package com.techbuild.techbuild.service;

import java.util.List;

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
		return saleDetailRepository.getReferenceById(id);
	}

	public List<SaleDetail> getSaleDetailsBySaleId(String saleId) {
		return saleDetailRepository.findBySaleId(saleId);
	}

	// UPDATE
	public SaleDetail updateSaleDetail(SaleDetail saleDetail) {
		return saleDetailRepository.saveAndFlush(saleDetail);
	}

	// DELETE
	public boolean deleteSaleDetail(SaleDetail saleDetail) {
		if (saleDetailRepository.existsById(saleDetail.getId())) {
			saleDetailRepository.delete(saleDetail);
			return true;
		}
		return false;
	}

	public boolean deleteSaleDetailById(String saleDetailId) {
		if (saleDetailRepository.existsById(saleDetailId)) {
			saleDetailRepository.deleteById(saleDetailId);
			return true;
		}
		return false;
	}
}
