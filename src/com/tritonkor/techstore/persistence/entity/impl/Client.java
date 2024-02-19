package com.tritonkor.techstore.persistence.entity.impl;

import com.tritonkor.techstore.persistence.entity.Entity;
import java.util.UUID;

public class Client extends Entity {

    private final  String username;
    private final String hashPassword;

    public Client(UUID id, String username, String hashPassword) {
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
}
