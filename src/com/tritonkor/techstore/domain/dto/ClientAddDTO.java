package com.tritonkor.techstore.domain.dto;

import com.tritonkor.techstore.persistence.entity.Entity;
import java.util.UUID;

public class ClientAddDTO extends Entity {

    private final  String username;
    private final String rawPassword;

    public ClientAddDTO(UUID id, String username, String rawPassword) {
        super(id);
        this.username = username;
        this.rawPassword = rawPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getRawPassword() {
        return rawPassword;
    }
}
