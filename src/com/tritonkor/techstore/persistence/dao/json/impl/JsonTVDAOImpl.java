package com.tritonkor.techstore.persistence.dao.json.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tritonkor.techstore.persistence.dao.contracts.TVDAO;
import com.tritonkor.techstore.persistence.entity.impl.TV;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class JsonTVDAOImpl implements TVDAO {

    private final String filePath;
    private final ObjectMapper objectMapper;

    public JsonTVDAOImpl(String filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public TV save(TV tv) throws IOException {
        Set<TV> tvs = findAll();
        tvs.add(tv);
        writeDataToFile(tvs);
        return tv;
    }

    @Override
    public boolean update(TV tv) throws IOException {
        Set<TV> tvs = findAll();
        tvs.removeIf(a -> a.getId() == tv.getId());
        tvs.add(tv);
        writeDataToFile(tvs);

        return true;
    }

    @Override
    public boolean delete(UUID tvId) throws IOException {
        Set<TV> tvs = findAll();
        tvs.removeIf(a -> a.getId() == tvId);
        writeDataToFile(tvs);
        return true;
    }

    @Override
    public Optional<TV> findById(UUID tvId) throws IOException {
        Set<TV> tvs = findAll();
        return tvs.stream()
                .filter(a -> a.getId() == tvId)
                .findFirst();
    }

    @Override
    public Set<TV> findAll() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException();
        }

        return objectMapper.readValue(file, new TypeReference<Set<TV>>() {});
    }

    private void writeDataToFile(Set<TV> tvs) throws IOException {
        File file = new File(filePath);
        objectMapper.writeValue(file, tvs);
    }

    @Override
    public TV findByModel(String model) throws IOException {
        Set<TV> tvs = findAll();
        return tvs.stream()
                .filter(a -> a.getModel() == model)
                .findFirst().orElse(null);
    }
}
