package com.techbuild.techbuild.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbuild.techbuild.dao.SaleNoteRepository;
import com.techbuild.techbuild.domain.SaleNote;

@Service
public class SaleNoteService {
	@Autowired
	private SaleNoteRepository saleNoteRepository;

	// CREATE
	public SaleNote createSaleNote(SaleNote saleNote) {
		return saleNoteRepository.save(saleNote);
	}

	// READ
	public List<SaleNote> getSaleNotes() {
		return saleNoteRepository.findAll();
	}

	public SaleNote getSaleNoteById(String id) {
		return saleNoteRepository.getReferenceById(id);
	}

	// UPDATE
	public SaleNote updateSaleNoteById(SaleNote saleNote) {
		return saleNoteRepository.save(saleNote);
	}

	// DELETE
	public boolean deleteSaleNote(SaleNote saleNote) {
		if (saleNoteRepository.existsById(saleNote.getId())) {
			saleNoteRepository.delete(saleNote);
			return true;
		}
		return false;
	}

	public boolean deleteSaleNoteById(String saleNoteId) {
		if (saleNoteRepository.existsById(saleNoteId)) {
			saleNoteRepository.deleteById(saleNoteId);
			return true;
		}
		return false;
	}
}
