package com.techbuild.techbuild.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techbuild.techbuild.domain.SaleNote;

public interface SaleNoteRepository extends JpaRepository<SaleNote, String> {

}
