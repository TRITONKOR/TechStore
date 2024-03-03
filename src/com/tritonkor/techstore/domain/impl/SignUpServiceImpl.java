package com.tritonkor.techstore.domain.impl;

import com.tritonkor.techstore.domain.contracts.SignUpService;
import com.tritonkor.techstore.domain.contracts.ClientService;
import com.tritonkor.techstore.domain.dto.ClientAddDTO;
import com.tritonkor.techstore.domain.exception.AuthException;
import com.tritonkor.techstore.domain.exception.SignUpException;
import com.tritonkor.techstore.persistence.dao.contracts.ClientDAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

/**
 * The SignUpServiceImpl class is an implementation of the SignUpService interface, providing user
 * registration functionality in the application.
 */
final class SignUpServiceImpl implements SignUpService {

    private final ClientService clientService;

    private final ClientDAO clientDAO;

    /**
     * Constructs a new SignUpServiceImpl with the specified UserService.
     *
     * @param clientService the user service used for user-related operations.
     */
    SignUpServiceImpl(ClientService clientService, ClientDAO clientDAO) {
        this.clientService = clientService;
        this.clientDAO = clientDAO;
    }

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
     * Registers a new user with the provided user details, input verification code, and the
     * generated verification code.
     *
     * @param clientAddDTO the user details for registration.
     * @throws SignUpException if the verification code is incorrect or has expired.
     */
    public void signUp(ClientAddDTO clientAddDTO) throws IOException {

        clientService.add(clientAddDTO);
    }
}
