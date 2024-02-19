package com.tritonkor.techstore.persistence.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tritonkor.techstore.persistence.contracts.TechniqueDAO;
import com.tritonkor.techstore.persistence.entity.Technique;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class JsonTechniqueDAOImpl implements TechniqueDAO {

    private final String filePath;
    private final ObjectMapper objectMapper;

    public JsonTechniqueDAOImpl(String filePath) {
        this.filePath = filePath;
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
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException();
        }

        return objectMapper.readValue(file, new TypeReference<Set<Technique>>() {});
    }

    private void writeDataToFile(Set<Technique> techniques) throws IOException {
        File file = new File(filePath);
        objectMapper.writeValue(file, techniques);
    }

    @Override
    public Technique findByModel(String model) throws IOException {
        Set<Technique> techniques = findAll();
        return techniques.stream()
                .filter(a -> a.getModel() == model)
                .findFirst().orElse(null);
    }
}
