package com.techbuild.techbuild.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techbuild.techbuild.domain.SaleNote;

public interface SaleNoteRepository extends JpaRepository<SaleNote, UUID> {

}
