package com.tritonkor.techstore.domain.impl;

import com.tritonkor.techstore.domain.Service;
import com.tritonkor.techstore.domain.exception.EntityNotFoundException;
import com.tritonkor.techstore.persistence.dao.DAO;
import com.tritonkor.techstore.persistence.entity.Entity;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class GenericService<E extends Entity> implements Service<E> {

    private final DAO<E> dao;

    public GenericService(DAO<E> dao) {
        this.dao = dao;
    }

    @Override
    public E get(UUID id) throws IOException {
        return dao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nothing found for this id"));
    }

    @Override
    public List<E> getAll() throws IOException {
        return dao.findAll();
    }

    @Override
    public E ad(E entity) throws IOException {
        return dao.save(entity);
    }

    @Override
    public boolean remove(E entity) throws IOException {
        return dao.delete(entity.getId());
    }
}
