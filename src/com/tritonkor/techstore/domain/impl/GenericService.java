package com.tritonkor.techstore.domain.impl;

import com.tritonkor.techstore.domain.Service;
import com.tritonkor.techstore.domain.exception.EntityNotFoundException;
import com.tritonkor.techstore.persistence.dao.DAO;
import com.tritonkor.techstore.persistence.entity.Entity;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * The {@code GenericService} class is an implementation of the {@code Service} interface,
 * providing generic service functionality for entities.
 *
 * @param <E> The type of the entity.
 */
public class GenericService<E extends Entity> implements Service<E> {

    /** The data access object for the entity type. */
    private final DAO<E> dao;

    /**
     * Constructs a new {@code GenericService} with the specified data access object.
     *
     * @param dao The data access object for the entity type.
     */
    public GenericService(DAO<E> dao) {
        this.dao = dao;
    }

    /**
     * Retrieves an entity by its unique identifier.
     *
     * @param id The unique identifier of the entity.
     * @return The entity with the specified ID.
     * @throws IOException if an I/O error occurs.
     * @throws EntityNotFoundException if the entity is not found.
     */
    @Override
    public E get(UUID id) throws IOException {
        return dao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nothing found for this id"));
    }

    /**
     * Retrieves all entities of the specified type.
     *
     * @return A list of all entities.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public List<E> getAll() throws IOException {
        return dao.findAll();
    }

    /**
     * Adds a new entity to the system.
     *
     * @param entity The entity to add.
     * @return The added entity.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public E ad(E entity) throws IOException {
        return dao.save(entity);
    }

    /**
     * Removes an entity from the system.
     *
     * @param entity The entity to remove.
     * @return {@code true} if the entity is successfully removed, {@code false} otherwise.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public boolean remove(E entity) throws IOException {
        return dao.delete(entity.getId());
    }
}
