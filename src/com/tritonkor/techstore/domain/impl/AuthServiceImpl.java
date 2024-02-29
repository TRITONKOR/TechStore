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

    private final ClientDAO clientDAO;

    private Client client;

    /**
     * Constructs a new AuthServiceImpl with the specified UserRepository.
     *
     * @param clientDAO the user repository used for user-related operations.
     */
    public AuthServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    /**
     * Authenticates a user with the given username and password.
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
     * Checks if a user is currently authenticated.
     *
     * @return true if the user is authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        return client != null;
    }

    /**
     * Gets the authenticated user.
     *
     * @return the authenticated user.
     */
    public Client getUser() {
        return client;
    }

}
