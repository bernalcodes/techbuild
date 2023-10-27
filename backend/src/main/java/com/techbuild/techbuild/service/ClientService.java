package com.techbuild.techbuild.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbuild.techbuild.dao.ClientRepository;
import com.techbuild.techbuild.domain.Client;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;

	// CREATE
	public Client createClient(Client client) {
		return clientRepository.save(client);
	}

	// READ
	public List<Client> getClients() {
		return clientRepository.findAll();
	}

	public Client getClientById(String id) {
		return clientRepository.getReferenceById(UUID.fromString(id));
	}

	public List<Client> getClientsByName(String name) {
		return clientRepository.findByName(name);
	}

	public List<Client> getClientsByLastName(String lastName) {
		return clientRepository.findByLastName(lastName);
	}

	public Client getClientByEmail(String email) {
		return clientRepository.findByEmail(email);
	}

	// UPDATE
	public Client updateClient(Client client) {
		return clientRepository.saveAndFlush(client);
	}

	// DELETE
	public boolean deleteClient(Client client) {
		UUID id = UUID.fromString(client.getId());
		if (clientRepository.existsById(id)) {
			clientRepository.delete(client);
			return true;
		}
		return false;
	}

	public boolean deleteClientById(String clientId) {
		UUID id = UUID.fromString(clientId);
		if (clientRepository.existsById(id)) {
			clientRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
