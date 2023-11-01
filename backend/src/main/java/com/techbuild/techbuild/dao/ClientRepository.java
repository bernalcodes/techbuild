package com.techbuild.techbuild.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techbuild.techbuild.domain.Client;

public interface ClientRepository extends JpaRepository<Client, String> {
	@Query("SELECT c FROM Client c WHERE c.name LIKE :name")
	public List<Client> findByName(@Param("name") String name);
	
	@Query("SELECT c FROM Client c WHERE c.lastName LIKE :lastName")
	public List<Client> findByLastName(@Param("lastName") String lastName);

	@Query("SELECT c FROM Client c WHERE c.email = :email")
	public Client findByEmail(@Param("email") String email);
}
