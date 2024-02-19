package com.tritonkor.techstore.persistence.entity;

import java.util.Objects;
import java.util.UUID;

public abstract class Entity {

    protected final UUID id;

    public Entity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }
}
