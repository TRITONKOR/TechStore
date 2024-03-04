package com.tritonkor.techstore.domain.impl;

import com.tritonkor.techstore.domain.contracts.ClientService;
import com.tritonkor.techstore.domain.dto.ClientAddDTO;
import com.tritonkor.techstore.domain.exception.EntityNotFoundException;
import com.tritonkor.techstore.domain.exception.SignUpException;
import com.tritonkor.techstore.persistence.dao.contracts.ClientDAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import java.io.IOException;
import java.util.UUID;
import org.mindrot.bcrypt.BCrypt;

/**
 * The {@code ClientServiceImpl} class is an implementation of the {@code ClientService} interface,
 * providing client-related functionality in the application.
 */
public class ClientServiceImpl extends GenericService<Client> implements ClientService {

    /** The client data access object used for client-related database operations. */
    private ClientDAO clientDAO;

    /**
     * Constructs a new {@code ClientServiceImpl} with the specified {@code ClientDAO}.
     *
     * @param clientDAO The client data access object used for client-related database operations.
     */
    public ClientServiceImpl(ClientDAO clientDAO) {
        super(clientDAO);
        this.clientDAO = clientDAO;
    }

    /**
     * Creates a new {@code ClientAddDTO} with the given username and raw password.
     *
     * @param username The username of the client.
     * @param rawPassword The raw password of the client.
     * @return The created {@code ClientAddDTO}.
     * @throws IOException if an I/O error occurs.
     */
    public ClientAddDTO createClient(String username, String rawPassword) throws IOException {
        ClientAddDTO clientAddDTO = new ClientAddDTO(UUID.randomUUID(), username, rawPassword);

        return clientAddDTO;
    }

    /**
     * Finds a client by username.
     *
     * @param username The username of the client to find.
     * @return The found client.
     * @throws IOException if an I/O error occurs.
     * @throws EntityNotFoundException if the client with the specified username is not found.
     */
    @Override
    public Client findByUsername(String username) throws IOException {
        return clientDAO.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("This user does not exist"));
    }

    /**
     * Adds a new client using the provided {@code ClientAddDTO}.
     *
     * @param clientAddDTO The client data transfer object containing client information.
     * @return The added client.
     * @throws IOException if an I/O error occurs.
     * @throws SignUpException if an error occurs during client registration.
     */
    @Override
    public Client add(ClientAddDTO clientAddDTO) throws IOException {
        try {
            var client = new Client(clientAddDTO.getId(), clientAddDTO.getUsername(),
                    BCrypt.hashpw(clientAddDTO.getRawPassword(), BCrypt.gensalt()));
            clientDAO.save(client);
            return client;
        } catch (RuntimeException e) {
            throw new SignUpException("Error when saving a client: %s"
                    .formatted(e.getMessage()));
        }
    }
}
