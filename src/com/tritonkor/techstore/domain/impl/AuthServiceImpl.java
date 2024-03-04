package com.tritonkor.techstore.domain.impl;

import com.tritonkor.techstore.domain.contracts.AuthService;
import com.tritonkor.techstore.domain.exception.AuthException;
import com.tritonkor.techstore.persistence.dao.contracts.ClientDAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import java.io.IOException;
import org.mindrot.bcrypt.BCrypt;

/**
 * The AuthServiceImpl class is an implementation of the AuthService interface, providing
 * authentication-related functionality in the application.
 */
public class AuthServiceImpl implements AuthService {

    /** The client repository used for client-related operations. */
    private final ClientDAO clientDAO;

    /** The currently authenticated client. */
    private Client client;

    /**
     * Constructs a new AuthServiceImpl with the specified UserRepository.
     *
     * @param clientDAO the client repository used for client-related operations.
     */
    public AuthServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    /**
     * Authenticates a client with the given username and password.
     *
     * @param username the username of the user.
     * @param password the password of the user.
     * @return true if authentication is successful.
     * @throws AuthException if authentication fails.
     */
    public boolean authenticate(String username, String password) throws IOException {
        Client foundedClient = clientDAO.findByUsername(username).orElseThrow(AuthException::new);

        if (!BCrypt.checkpw(password, foundedClient.getHashPassword())) {
            throw new AuthException();
        }

        client = foundedClient;
        return true;
    }

    /**
     * Checks if a client is currently authenticated.
     *
     * @return true if the client is authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        return client != null;
    }

    /**
     * Gets the authenticated client.
     *
     * @return the authenticated client.
     */
    public Client getClient() {
        return client;
    }

}
