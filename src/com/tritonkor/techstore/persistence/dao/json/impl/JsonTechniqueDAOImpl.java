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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the {@link TechniqueDAO} interface using JSON files for data storage.
 */
public class JsonTechniqueDAOImpl implements TechniqueDAO {

    /** The file path where client data is stored in JSON format. */
    private final Path filePath;

    /** ObjectMapper for JSON serialization and deserialization. */
    private final ObjectMapper objectMapper;

    /**
     * Constructs a new instance of JsonTechniqueDAOImpl.
     */
    public JsonTechniqueDAOImpl() {
        this.filePath = JsonPathFactory.TECHNIQUES.getPath();
        this.objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Saves a new technique to the JSON file.
     *
     * @param technique The technique to save.
     * @return The saved technique.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public Technique save(Technique technique) throws IOException {
        List<Technique> techniques = findAll();
        techniques.add(technique);
        writeDataToFile(techniques);
        return technique;
    }

    /**
     * Updates an existing technique in the JSON file.
     *
     * @param technique The technique to update.
     * @return True if the update is successful, false otherwise.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public boolean update(Technique technique) throws IOException {
        List<Technique> techniques = findAll();
        techniques.removeIf(a -> a.getId() == technique.getId());
        techniques.add(technique);
        writeDataToFile(techniques);

        return true;
    }

    /**
     * Deletes a technique from the JSON file.
     *
     * @param techniqueId The ID of the technique to delete.
     * @return True if the deletion is successful, false otherwise.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public boolean delete(UUID techniqueId) throws IOException {
        List<Technique> techniques = findAll();
        techniques.removeIf(a -> a.getId() == techniqueId);
        writeDataToFile(techniques);
        return true;
    }

    /**
     * Finds a technique by its ID.
     *
     * @param techniqueId The ID of the technique to find.
     * @return An Optional containing the found technique, or empty if not found.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public Optional<Technique> findById(UUID techniqueId) throws IOException {
        List<Technique> techniques = findAll();
        return techniques.stream()
                .filter(a -> a.getId() == techniqueId)
                .findFirst();
    }

    /**
     * Retrieves all techniques from the JSON file.
     *
     * @return A list of all techniques.
     * @throws IOException If an I/O error occurs.
     */
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

    /**
     * Writes a list of techniques to the data store file.
     *
     * @param techniques The list of techniques to be written.
     * @throws IOException If an I/O error occurs during the write operation.
     */
    private void writeDataToFile(List<Technique> techniques) throws IOException {
        File file = new File(filePath.toString());
        objectMapper.writeValue(file, techniques);
    }

    /**
     * Retrieves all techniques with a specific model from the JSON file.
     *
     * @param model The model of the techniques to retrieve.
     * @return A list of techniques with the specified model.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public List<Technique> findAllByModel(String model) throws IOException {
        List<Technique> techniques = findAll();
        return techniques.stream()
                .filter(a -> a.getModel().equals(model)).toList();
    }
}
