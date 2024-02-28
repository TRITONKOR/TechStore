package com.tritonkor.techstore.persistence.dao.json.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tritonkor.techstore.persistence.dao.contracts.TechniqueDAO;
import com.tritonkor.techstore.persistence.entity.impl.Technique;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class JsonTechniqueDAOImpl implements TechniqueDAO {

    private final Path filePath;
    private final ObjectMapper objectMapper;

    public JsonTechniqueDAOImpl() {
        this.filePath = JsonPathFactory.TECHNIQUES.getPath();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Technique save(Technique technique) throws IOException {
        Set<Technique> techniques = findAll();
        techniques.add(technique);
        writeDataToFile(techniques);
        return technique;
    }

    @Override
    public boolean update(Technique technique) throws IOException {
        Set<Technique> techniques = findAll();
        techniques.removeIf(a -> a.getId() == technique.getId());
        techniques.add(technique);
        writeDataToFile(techniques);

        return true;
    }

    @Override
    public boolean delete(UUID techniqueId) throws IOException {
        Set<Technique> techniques = findAll();
        techniques.removeIf(a -> a.getId() == techniqueId);
        writeDataToFile(techniques);
        return true;
    }

    @Override
    public Optional<Technique> findById(UUID techniqueId) throws IOException {
        Set<Technique> techniques = findAll();
        return techniques.stream()
                .filter(a -> a.getId() == techniqueId)
                .findFirst();
    }

    @Override
    public Set<Technique> findAll() throws IOException {
        File file = new File(filePath.toString());
        if (!file.exists()) {
            throw new IOException();
        }

        return objectMapper.readValue(file, new TypeReference<Set<Technique>>() {});
    }

    private void writeDataToFile(Set<Technique> techniques) throws IOException {
        File file = new File(filePath.toString());
        objectMapper.writeValue(file, techniques);
    }

    @Override
    public Set<Technique> findAllByModel(String model) throws IOException {
        Set<Technique> techniques = findAll();
        return techniques.stream()
                .filter(a -> a.getModel().equals(model)).collect(Collectors.toSet());
    }
}
