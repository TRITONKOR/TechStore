package com.tritonkor.techstore.domain.contracts;

import com.tritonkor.techstore.persistence.entity.impl.Client;
import java.io.IOException;

/**
 * The AuthService interface defines the contract for user authentication and authorization
 * operations. It provides methods for authenticating users, checking authentication status,
 * retrieving authenticated user details, and logging out.
 */
public interface AuthService {

    /**
     * Authenticates a client based on the provided username and password.
     *
     * @param username The username of the user attempting to authenticate.
     * @param password The password of the user attempting to authenticate.
     * @return true if the authentication is successful, false otherwise.
     */
    boolean authenticate(String username, String password) throws IOException;

    /**
     * Checks if a client is currently authenticated.
     *
     * @return true if a user is authenticated, false otherwise.
     */
    boolean isAuthenticated();

    /**
     * Retrieves the details of the authenticated client.
     *
     * @return The Client object representing the authenticated client, or null if no client is
     * authenticated.
     */
    Client getClient();
}
