package com.techbuild.techbuild.service;

import java.util.List;
import java.util.UUID;

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
		return saleNoteRepository.getReferenceById(UUID.fromString(id));
	}

	// UPDATE
	public SaleNote updateSaleNoteById(SaleNote saleNote) {
		return saleNoteRepository.save(saleNote);
	}

	// DELETE
	public boolean deleteSaleNote(SaleNote saleNote) {
		UUID id = UUID.fromString(saleNote.getId());
		if (saleNoteRepository.existsById(id)) {
			saleNoteRepository.delete(saleNote);
			return true;
		}
		return false;
	}

	public boolean deleteSaleNoteById(String saleNoteId) {
		UUID id = UUID.fromString(saleNoteId);
		if (saleNoteRepository.existsById(id)) {
			saleNoteRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
