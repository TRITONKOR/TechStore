package com.tritonkor.techstore.domain.contracts;

import com.tritonkor.techstore.domain.dto.ClientAddDTO;
import java.io.IOException;

/**
 * The SignUpService interface defines the contract for user signup operations and verification code
 * management. It provides methods for user signup, generating and sending verification codes.
 */
public interface SignUpService {

    /**
     * Signs up a new user based on the provided UserAddDto and verifies the user with the given
     * verification code.
     *
     * @param clientAddDTO    The DTO containing information to create the new user.
     */
    void signUp(ClientAddDTO clientAddDTO) throws IOException;

    boolean checkUsernameAvailability(String username) throws IOException;
}
