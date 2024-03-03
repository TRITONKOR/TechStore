package com.tritonkor.techstore.persistence.dao.json.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tritonkor.techstore.persistence.dao.contracts.TechniqueDAO;
import com.tritonkor.techstore.persistence.entity.impl.Technique;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class JsonTechniqueDAOImpl implements TechniqueDAO {

    private final Path filePath;
    private final ObjectMapper objectMapper;

    public JsonTechniqueDAOImpl() {
        this.filePath = JsonPathFactory.TECHNIQUES.getPath();
        this.objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public Technique save(Technique technique) throws IOException {
        List<Technique> techniques = findAll();
        techniques.add(technique);
        writeDataToFile(techniques);
        return technique;
    }

    @Override
    public boolean update(Technique technique) throws IOException {
        List<Technique> techniques = findAll();
        techniques.removeIf(a -> a.getId() == technique.getId());
        techniques.add(technique);
        writeDataToFile(techniques);

        return true;
    }

    @Override
    public boolean delete(UUID techniqueId) throws IOException {
        List<Technique> techniques = findAll();
        techniques.removeIf(a -> a.getId() == techniqueId);
        writeDataToFile(techniques);
        return true;
    }

    @Override
    public Optional<Technique> findById(UUID techniqueId) throws IOException {
        List<Technique> techniques = findAll();
        return techniques.stream()
                .filter(a -> a.getId() == techniqueId)
                .findFirst();
    }

    @Override
    public List<Technique> findAll() throws IOException {
        File file = new File(filePath.toString());
        if (!file.exists()) {
            Files.createFile(filePath);
        }
        if (file.length() == 0) {
            return new ArrayList<>();
        }

        return objectMapper.readValue(file, new TypeReference<List<Technique>>() {});
    }

    private void writeDataToFile(List<Technique> techniques) throws IOException {
        File file = new File(filePath.toString());
        objectMapper.writeValue(file, techniques);
    }

    @Override
    public List<Technique> findAllByModel(String model) throws IOException {
        List<Technique> techniques = findAll();
        return techniques.stream()
                .filter(a -> a.getModel().equals(model)).toList();
    }
}
