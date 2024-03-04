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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the {@link ClientDAO} interface that stores and retrieves Client entities in JSON format.
 */
public class JsonClientDAOImpl implements ClientDAO {

    /** The file path where client data is stored in JSON format. */
    private final Path filePath;

    /** ObjectMapper for JSON serialization and deserialization. */
    private final ObjectMapper objectMapper;

    /**
     * Constructs a new instance of JsonClientDAOImpl.
     */
    public JsonClientDAOImpl() {
        this.filePath = JsonPathFactory.CLIENTS.getPath();
        this.objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Saves a client to the data store.
     *
     * @param client The client to be saved.
     * @return The saved client.
     * @throws IOException If an I/O error occurs during the save operation.
     */
    @Override
    public Client save(Client client) throws IOException {
        List<Client> clients = findAll();
        clients.add(client);
        writeDataToFile(clients);
        return client;
    }

    /**
     * Updates an existing client in the data store.
     *
     * @param client The client to be updated.
     * @return True if the update is successful, false otherwise.
     * @throws IOException If an I/O error occurs during the update operation.
     */
    @Override
    public boolean update(Client client) throws IOException {
        List<Client> clients = findAll();
        clients.removeIf(a -> a.getId() == client.getId());
        clients.add(client);
        writeDataToFile(clients);

        return true;
    }

    /**
     * Deletes a client from the data store by its ID.
     *
     * @param clientId The ID of the client to be deleted.
     * @return True if the deletion is successful, false otherwise.
     * @throws IOException If an I/O error occurs during the delete operation.
     */
    @Override
    public boolean delete(UUID clientId) throws IOException {
        List<Client> clients = findAll();
        clients.removeIf(a -> a.getId() == clientId);
        writeDataToFile(clients);
        return true;
    }

    /**
     * Finds a client in the data store by its ID.
     *
     * @param clientId The ID of the client to be found.
     * @return An Optional containing the found client, or an empty Optional if not found.
     * @throws IOException If an I/O error occurs during the find operation.
     */
    @Override
    public Optional<Client> findById(UUID clientId) throws IOException {
        List<Client> clients = findAll();
        return clients.stream()
                .filter(a -> a.getId() == clientId)
                .findFirst();
    }

    /**
     * Retrieves all clients from the data store.
     *
     * @return A List containing all clients in the data store.
     * @throws IOException If an I/O error occurs during the retrieval operation.
     */
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

    /**
     * Writes a list of clients to the data store file.
     *
     * @param clients The list of clients to be written.
     * @throws IOException If an I/O error occurs during the write operation.
     */
    private void writeDataToFile(List<Client> clients) throws IOException {
        File file = new File(filePath.toString());
        objectMapper.writeValue(file, clients);
    }

    /**
     * Finds a client in the data store by its username.
     *
     * @param username The username of the client to be found.
     * @return An Optional containing the found client, or an empty Optional if not found.
     * @throws IOException If an I/O error occurs during the find operation.
     */
    @Override
    public Optional<Client> findByUsername(String username) throws IOException {
        List<Client> clients = findAll();
        return clients.stream()
                .filter(a -> a.getUsername().equals(username))
                .findFirst();
    }
}
