package com.tritonkor.techstore.domain.contracts;

import com.tritonkor.techstore.domain.Service;
import com.tritonkor.techstore.domain.dto.ClientAddDTO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import java.io.IOException;

/**
 * The {@code ClientService} interface defines the contract for services related to the Client entity.
 * Extends the base Service interface for common CRUD operations.
 */
public interface ClientService extends Service<Client> {

    /**
     * Retrieves a Client entity based on the provided username.
     *
     * @param username The username associated with the Client to retrieve.
     * @return The Client entity with the specified username.
     * @throws IOException If an I/O error occurs while retrieving data.
     */
    Client findByUsername(String username) throws IOException;

    /**
     * Adds a new Client based on the provided ClientAddDTO.
     *
     * @param clientAddDTO The DTO containing information to create a new Client.
     * @return The newly added Client entity.
     * @throws IOException If an I/O error occurs while adding the Client.
     */
    Client add(ClientAddDTO clientAddDTO) throws IOException;

    /**
     * Creates a new ClientAddDTO with the specified username and raw password.
     *
     * @param username The username for the new client.
     * @param rawPassword The raw password for the new client.
     * @return The created ClientAddDTO.
     * @throws IOException If an I/O error occurs while creating the ClientAddDTO.
     */
    ClientAddDTO createClient(String username, String rawPassword) throws IOException;
}
