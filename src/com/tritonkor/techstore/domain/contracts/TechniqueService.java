package com.tritonkor.techstore.domain.contracts;

import com.tritonkor.techstore.domain.Service;
import com.tritonkor.techstore.domain.dto.TechniqueAddDTO;
import com.tritonkor.techstore.persistence.entity.impl.Technique;
import java.io.IOException;
import java.util.List;

/**
 * The {@code TechniqueService} interface defines the contract for services related to the Technique entity.
 * Extends the base Service interface for common CRUD operations.
 */
public interface TechniqueService extends Service<Technique> {

    /**
     * Retrieves a list of Technique entities with a specified model.
     *
     * @param model The model to filter the list of techniques.
     * @return A list of Technique entities matching the specified model.
     * @throws IOException If an I/O error occurs while retrieving data.
     */
    List<Technique> findAllByModel(String model) throws IOException;

    /**
     * Adds a new Technique based on the provided TechniqueAddDTO.
     *
     * @param techniqueAddDTO The DTO containing information to create a new Technique.
     * @return The newly added Technique entity.
     * @throws IOException If an I/O error occurs while adding the Technique.
     */
    Technique add(TechniqueAddDTO techniqueAddDTO) throws IOException;
}
