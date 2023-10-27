package com.techbuild.techbuild.service;

import java.util.List;
import java.util.UUID;

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
		return paymentTypeRepository.getReferenceById(UUID.fromString(id));
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
		UUID id = UUID.fromString(paymentType.getId());
		if (paymentTypeRepository.existsById(id)) {
			paymentTypeRepository.delete(paymentType);
			return true;
		}
		return false;
	}

	public boolean deletePaymentTypeById(String paymentTypeId) {
		UUID id = UUID.fromString(paymentTypeId);
		if (paymentTypeRepository.existsById(id)) {
			paymentTypeRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
