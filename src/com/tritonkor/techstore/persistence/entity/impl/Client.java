package com.tritonkor.techstore.persistence.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tritonkor.techstore.persistence.entity.Entity;
import java.util.UUID;

/**
 * Represents a client entity in the system with a unique identifier, username, and hashed password.
 */
@JsonPropertyOrder({"id", "username", "hashPassword"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client extends Entity {

    /** The username of the client. */
    private final String username;

    /** The hashed password of the client. */
    private final String hashPassword;

    /**
     * Constructs a client with the specified identifier, username, and hashed password.
     *
     * @param id           The unique identifier for the client.
     * @param username     The username of the client.
     * @param hashPassword The hashed password of the client.
     */
    public Client(@JsonProperty("id") UUID id, @JsonProperty("username") String username,
            @JsonProperty("hashPassword") String hashPassword) {
        super(id);
        this.username = username;
        this.hashPassword = hashPassword;
    }

    /**
     * Gets the username of the client.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the hashed password of the client.
     *
     * @return The hashed password.
     */
    public String getHashPassword() {
        return hashPassword;
    }

    /**
     * Returns a string representation of the client.
     *
     * @return A string representation in the format "Client{username='...', hashPassword='...'}".
     */
    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                '}';
    }
}
