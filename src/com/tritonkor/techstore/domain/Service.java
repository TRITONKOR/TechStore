package com.tritonkor.techstore.domain;

import com.tritonkor.techstore.persistence.entity.Entity;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public interface Service<E extends Entity> {

    E get(UUID id) throws IOException;

    Set<E> getAll() throws IOException;

    E ad(E entity) throws IOException;

    boolean remove(E entity) throws IOException;
}
