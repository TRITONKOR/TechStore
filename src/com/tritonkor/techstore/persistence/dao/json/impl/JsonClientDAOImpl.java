package com.tritonkor.techstore.persistence.dao.json.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.tritonkor.techstore.persistence.dao.contracts.ClientDAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class JsonClientDAOImpl implements ClientDAO {

    private final Path filePath;
    private final ObjectMapper objectMapper;

    public JsonClientDAOImpl() {
        this.filePath = JsonPathFactory.CLIENTS.getPath();
        this.objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public Client save(Client client) throws IOException {
        List<Client> clients = findAll();
        clients.add(client);
        writeDataToFile(clients);
        return client;
    }

    @Override
    public boolean update(Client client) throws IOException {
        List<Client> clients = findAll();
        clients.removeIf(a -> a.getId() == client.getId());
        clients.add(client);
        writeDataToFile(clients);

        return true;
    }

    @Override
    public boolean delete(UUID clientId) throws IOException {
        List<Client> clients = findAll();
        clients.removeIf(a -> a.getId() == clientId);
        writeDataToFile(clients);
        return true;
    }

    @Override
    public Optional<Client> findById(UUID clientId) throws IOException {
        List<Client> clients = findAll();
        return clients.stream()
                .filter(a -> a.getId() == clientId)
                .findFirst();
    }

    @Override
    public List<Client> findAll() throws IOException {
        File file = new File(filePath.toString());
        if (!file.exists()) {
            Files.createFile(filePath);
        }
        if (file.length() == 0) {
            return new ArrayList<>();
        }

        return objectMapper.readValue(file, new TypeReference<List<Client>>() {});
    }

    private void writeDataToFile(List<Client> clients) throws IOException {
        File file = new File(filePath.toString());
        objectMapper.writeValue(file, clients);
    }

    @Override
    public Optional<Client> findByUsername(String username) throws IOException {
        List<Client> clients = findAll();
        return clients.stream()
                .filter(a -> a.getUsername().equals(username))
                .findFirst();
    }
}
