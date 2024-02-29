package com.tritonkor.techstore.domain.impl;

import com.tritonkor.techstore.domain.contracts.SignUpService;
import com.tritonkor.techstore.domain.contracts.ClientService;
import com.tritonkor.techstore.domain.dto.ClientAddDTO;
import com.tritonkor.techstore.domain.exception.SignUpException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

/**
 * The SignUpServiceImpl class is an implementation of the SignUpService interface, providing user
 * registration functionality in the application.
 */
final class SignUpServiceImpl implements SignUpService {

    private static final int VERIFICATION_CODE_EXPIRATION_MINUTES = 1;
    private static LocalDateTime codeCreationTime;
    private final ClientService clientService;

    /**
     * Constructs a new SignUpServiceImpl with the specified UserService.
     *
     * @param clientService the user service used for user-related operations.
     */
    SignUpServiceImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Registers a new user with the provided user details, input verification code, and the
     * generated verification code.
     *
     * @param clientAddDTO       the user details for registration.
     * @throws SignUpException if the verification code is incorrect or has expired.
     */
    public void signUp(ClientAddDTO clientAddDTO) throws IOException {

        clientService.add(clientAddDTO);
    }
}
