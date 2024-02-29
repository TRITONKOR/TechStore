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
     * Authenticates a user based on the provided username and password.
     *
     * @param username The username of the user attempting to authenticate.
     * @param password The password of the user attempting to authenticate.
     * @return true if the authentication is successful, false otherwise.
     */
    boolean authenticate(String username, String password) throws IOException;

    /**
     * Checks if a user is currently authenticated.
     *
     * @return true if a user is authenticated, false otherwise.
     */
    boolean isAuthenticated();

    /**
     * Retrieves the details of the authenticated user.
     *
     * @return The User object representing the authenticated user, or null if no user is
     * authenticated.
     */
    Client getUser();
}
