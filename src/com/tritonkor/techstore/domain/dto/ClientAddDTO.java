package com.tritonkor.techstore.domain.dto;

import com.tritonkor.techstore.persistence.entity.Entity;
import java.util.UUID;

public class ClientAddDTO extends Entity {

    private final  String username;
    private final String hashPassword;

    public ClientAddDTO(UUID id, String username, String hashPassword) {
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
