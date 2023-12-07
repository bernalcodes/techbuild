package com.techbuild.techbuild.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techbuild.techbuild.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
	@Query("SELECT u FROM User u WHERE u.name LIKE :name")
	public List<User> findByName(@Param("name") String name);

	@Query("SELECT u FROM User u WHERE u.lastName LIKE :lastName")
	public List<User> findByLastName(@Param("lastName") String lastName);

	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User findByEmail(@Param("email") String email);
}
