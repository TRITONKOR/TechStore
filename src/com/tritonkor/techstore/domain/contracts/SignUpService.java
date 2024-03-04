package com.tritonkor.techstore.domain.contracts;

import com.tritonkor.techstore.domain.dto.ClientAddDTO;
import java.io.IOException;

/**
 * The SignUpService interface defines the contract for client signup operations
 */
public interface SignUpService {

    /**
     * Signs up a new client based on the provided ClientAddDTO
     *
     * @param clientAddDTO    The DTO containing information to create the new client.
     */
    void signUp(ClientAddDTO clientAddDTO) throws IOException;

    boolean checkUsernameAvailability(String username) throws IOException;
}
