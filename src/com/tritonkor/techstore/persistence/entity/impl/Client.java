package com.tritonkor.techstore.persistence.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tritonkor.techstore.persistence.entity.Entity;
import java.util.UUID;

@JsonPropertyOrder({"id", "username", "hashPassword"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client extends Entity {

    private final String username;
    private final String hashPassword;

    public Client(@JsonProperty("id") UUID id, @JsonProperty("username") String username,
            @JsonProperty("hashPassword") String hashPassword) {
        super(id);
        this.username = username;
        this.hashPassword = hashPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                '}';
    }
}
