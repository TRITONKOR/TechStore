package com.tritonkor.techstore.persistence.entity;

import java.util.Objects;
import java.util.UUID;

/**
 * This abstract class represents the base entity in the system with a unique identifier (UUID).
 * Subclasses of this entity are expected to extend from this class to inherit the common properties.
 */
public abstract class Entity {

    /**
     * The unique identifier for the entity.
     */
    protected final UUID id;

    /**
     * Constructs an entity with the specified identifier.
     *
     * @param id The unique identifier for the entity.
     */
    public Entity(UUID id) {
        this.id = id;
    }

    /**
     * Gets the unique identifier of the entity.
     *
     * @return The unique identifier.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Compares this entity with the specified object for equality.
     *
     * @param o The object to compare with.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
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
