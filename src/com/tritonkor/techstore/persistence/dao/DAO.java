package com.tritonkor.techstore.persistence.dao;

import com.tritonkor.techstore.persistence.entity.Entity;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Generic Data Access Object (DAO) interface for entities with basic CRUD operations.
 *
 * @param <E> The type of entity.
 */
public interface DAO<E extends Entity> {

    /**
     * Finds an entity by its unique identifier.
     *
     * @param id The unique identifier of the entity to find.
     * @return An Optional containing the found entity, or an empty Optional if not found.
     * @throws IOException If there is an issue accessing the data store.
     */
    Optional<E> findById(UUID id) throws IOException;

    /**
     * Retrieves all entities.
     *
     * @return A List containing all entities.
     * @throws IOException If there is an issue accessing the data store.
     */
    List<E> findAll() throws IOException;

    /**
     * Saves an entity.
     *
     * @param entity The entity to save.
     * @return The saved entity.
     * @throws IOException If there is an issue accessing the data store.
     */
    E save(E entity) throws IOException;

    /**
     * Updates an existing entity.
     *
     * @param entity The entity to update.
     * @return True if the update is successful, false otherwise.
     * @throws IOException If there is an issue accessing the data store.
     */
    boolean update(E entity) throws IOException;

    /**
     * Deletes an entity by its unique identifier.
     *
     * @param id The unique identifier of the entity to delete.
     * @return True if the deletion is successful, false otherwise.
     * @throws IOException If there is an issue accessing the data store.
     */
    boolean delete(UUID id) throws IOException;
}
