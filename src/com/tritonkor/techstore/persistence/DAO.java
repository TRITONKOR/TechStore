package com.tritonkor.techstore.persistence;

import com.tritonkor.techstore.persistence.entity.Entity;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface DAO<E extends Entity> {

    Optional<E> findById(UUID id) throws IOException;

    Set<E> findAll() throws IOException;

    E save(E entity) throws IOException;

    boolean update(E entity) throws IOException;

    boolean delete(UUID id) throws IOException;
}
