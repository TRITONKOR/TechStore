package com.tritonkor.techstore.persistence.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import com.tritonkor.techstore.persistence.contracts.ClientDAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class JsonClientDAOImpl implements ClientDAO {

    private final String filePath;
    private final ObjectMapper objectMapper;

    public JsonClientDAOImpl(String filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Client save(Client client) throws IOException {
        Set<Client> clients = findAll();
        clients.add(client);
        writeDataToFile(clients);
        return client;
    }

    @Override
    public boolean update(Client client) throws IOException {
        Set<Client> clients = findAll();
        clients.removeIf(a -> a.getId() == client.getId());
        clients.add(client);
        writeDataToFile(clients);

        return true;
    }

    @Override
    public boolean delete(UUID clientId) throws IOException {
        Set<Client> clients = findAll();
        clients.removeIf(a -> a.getId() == clientId);
        writeDataToFile(clients);
        return true;
    }

    @Override
    public Optional<Client> findById(UUID clientId) throws IOException {
        Set<Client> clients = findAll();
        return clients.stream()
                .filter(a -> a.getId() == clientId)
                .findFirst();
    }

    @Override
    public Set<Client> findAll() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException();
        }

        return objectMapper.readValue(file, new TypeReference<Set<Client>>() {});
    }

    private void writeDataToFile(Set<Client> clients) throws IOException {
        File file = new File(filePath);
        objectMapper.writeValue(file, clients);
    }

    @Override
    public Client findByUsername(String username) throws IOException {
        Set<Client> clients = findAll();
        return clients.stream()
                .filter(a -> a.getUsername() == username)
                .findFirst().orElse(null);
    }
}
