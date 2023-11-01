package com.techbuild.techbuild.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbuild.techbuild.dao.PaymentTypeRepository;
import com.techbuild.techbuild.domain.PaymentType;

@Service
public class PaymentTypeService {
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;

	// CREATE
	public PaymentType createPaymentType(PaymentType paymentType) {
		return paymentTypeRepository.save(paymentType);
	}

	// READ
	public List<PaymentType> getPaymentTypes() {
		return paymentTypeRepository.findAll();
	}

	public PaymentType getPaymentTypeById(String id) {
		return paymentTypeRepository.getReferenceById(id);
	}

	public List<PaymentType> getPaymentTypesByName(String name) {
		return paymentTypeRepository.findByName(name);
	}

	// UPDATE
	public PaymentType updatePaymentType(PaymentType paymentType) {
		return paymentTypeRepository.saveAndFlush(paymentType);
	}

	// DELETE
	public boolean deletePaymentType(PaymentType paymentType) {
		if (paymentTypeRepository.existsById(paymentType.getId())) {
			paymentTypeRepository.delete(paymentType);
			return true;
		}
		return false;
	}

	public boolean deletePaymentTypeById(String paymentTypeId) {
		if (paymentTypeRepository.existsById(paymentTypeId)) {
			paymentTypeRepository.deleteById(paymentTypeId);
			return true;
		}
		return false;
	}
}
