package com.techbuild.techbuild.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbuild.techbuild.dao.UserRepository;
import com.techbuild.techbuild.domain.User;

@Service
public class UserService {
	@Autowired
	private UserRepository UserRepository;

	// CREATE
	public User createUser(User user) {
		return UserRepository.save(user);
	}

	// READ
	public List<User> getUsers() {
		return UserRepository.findAll();
	}

	public User getUserById(String id) {
		return UserRepository.getReferenceById(id);
	}

	public List<User> getUsersByName(String name) {
		return UserRepository.findByName(name);
	}

	public List<User> getUsersByLastName(String lastName) {
		return UserRepository.findByLastName(lastName);
	}

	public User getUserByEmail(String email) {
		return UserRepository.findByEmail(email);
	}

	// UPDATE
	public User updateUser(User User) {
		return UserRepository.saveAndFlush(User);
	}

	// DELETE
	public boolean deleteUser(User user) {
		if (UserRepository.existsById(user.getId())) {
			UserRepository.delete(user);
			return true;
		}
		return false;
	}

	public boolean deleteUserById(String userId) {
		if (UserRepository.existsById(userId)) {
			UserRepository.deleteById(userId);
			return true;
		}
		return false;
	}
}
