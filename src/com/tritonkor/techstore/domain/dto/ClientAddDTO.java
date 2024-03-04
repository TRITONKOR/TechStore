package com.tritonkor.techstore.domain.dto;

import com.tritonkor.techstore.persistence.entity.Entity;
import java.util.UUID;

/**
 * The {@code ClientAddDTO} class represents a Data Transfer Object (DTO) for creating a new Client.
 * Extends the base Entity class to inherit the common identifier field.
 */
public class ClientAddDTO extends Entity {

    /** The username for the new client. */
    private final  String username;

    /** The raw password for the new client. */
    private final String rawPassword;

    /**
     * Constructs a new instance of ClientAddDTO.
     *
     * @param id The unique identifier for the DTO.
     * @param username The username for the new client.
     * @param rawPassword The raw password for the new client.
     */
    public ClientAddDTO(UUID id, String username, String rawPassword) {
        super(id);
        this.username = username;
        this.rawPassword = rawPassword;
    }

    /**
     * Gets the username for the new client.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the raw password for the new client.
     *
     * @return The raw password.
     */
    public String getRawPassword() {
        return rawPassword;
    }
}
