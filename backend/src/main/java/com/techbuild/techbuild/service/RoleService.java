package com.techbuild.techbuild.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbuild.techbuild.dao.RoleRepository;
import com.techbuild.techbuild.domain.Role;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;

	// CREATE
	public Role createRole(Role role) {
		return roleRepository.save(role);
	}

	// READ
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	public List<Role> getRolesByName(String name) {
		return roleRepository.findByName(name);
	}

	public Role getRoleById(String id) {
		return roleRepository.getReferenceById(UUID.fromString(id));
	}

	// UPDATE
	public Role updateRole(Role role) {
		return roleRepository.save(role);
	}

	// DELETE
	public boolean deleteRole(Role role) {
		UUID id = UUID.fromString(role.getId());
		if (roleRepository.existsById(id)) {
			roleRepository.delete(role);
			return true;
		}
		return false;
	}

	public boolean deleteRoleById(String id) {
		UUID uuid = UUID.fromString(id);
		if (roleRepository.existsById(uuid)) {
			roleRepository.deleteById(uuid);
			return true;
		}
		return false;
	}
}
