package com.tritonkor.techstore.domain.impl;

import com.tritonkor.techstore.domain.contracts.SignUpService;
import com.tritonkor.techstore.domain.contracts.ClientService;
import com.tritonkor.techstore.domain.dto.ClientAddDTO;
import com.tritonkor.techstore.domain.exception.AuthException;
import com.tritonkor.techstore.domain.exception.SignUpException;
import com.tritonkor.techstore.persistence.dao.contracts.ClientDAO;
import java.io.IOException;

/**
 * The SignUpServiceImpl class is an implementation of the SignUpService interface, providing user
 * registration functionality in the application.
 */
final class SignUpServiceImpl implements SignUpService {

    /** The client service used for user-related operations. */
    private final ClientService clientService;

    /** The client data access object. */
    private final ClientDAO clientDAO;

    /**
     * Constructs a new SignUpServiceImpl with the specified ClientService.
     *
     * @param clientService the client service used for client-related operations.
     */
    SignUpServiceImpl(ClientService clientService, ClientDAO clientDAO) {
        this.clientService = clientService;
        this.clientDAO = clientDAO;
    }

    /**
     * Checks the availability of a username for registration.
     *
     * @param username The username to check.
     * @return {@code true} if the username is available, {@code false} otherwise.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public boolean checkUsernameAvailability(String username) throws IOException {
        try {
            clientDAO.findByUsername(username)
                    .orElseThrow(AuthException::new);
        } catch (AuthException e) {
            return true;
        }
        return false;
    }

    /**
     * Registers a new client with the provided client details
     *
     * @param clientAddDTO the client details for registration.
     * @throws IOException if an I/O error occurs.
     */
    public void signUp(ClientAddDTO clientAddDTO) throws IOException {

        clientService.add(clientAddDTO);
    }
}
