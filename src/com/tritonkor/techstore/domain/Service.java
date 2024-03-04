package com.tritonkor.techstore.domain;

import com.tritonkor.techstore.persistence.entity.Entity;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * The {@code Service} interface represents a generic service interface for entities.
 *
 * @param <E> The type of entity.
 */
public interface Service<E extends Entity> {

    /**
     * Retrieves an entity by its unique identifier.
     *
     * @param id The unique identifier of the entity.
     * @return The entity with the specified identifier.
     * @throws IOException if an I/O error occurs.
     */
    E get(UUID id) throws IOException;

    /**
     * Retrieves all entities of the specified type.
     *
     * @return A list of all entities.
     * @throws IOException if an I/O error occurs.
     */
    List<E> getAll() throws IOException;

    /**
     * Adds a new entity to the service.
     *
     * @param entity The entity to be added.
     * @return The added entity.
     * @throws IOException if an I/O error occurs.
     */
    E ad(E entity) throws IOException;

    /**
     * Removes the specified entity from the service.
     *
     * @param entity The entity to be removed.
     * @return {@code true} if the removal is successful, {@code false} otherwise.
     * @throws IOException if an I/O error occurs.
     */
    boolean remove(E entity) throws IOException;
}
