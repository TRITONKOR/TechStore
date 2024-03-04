package com.tritonkor.techstore.domain.impl;

import com.tritonkor.techstore.domain.contracts.TechniqueService;
import com.tritonkor.techstore.domain.dto.TechniqueAddDTO;
import com.tritonkor.techstore.persistence.dao.contracts.TechniqueDAO;
import com.tritonkor.techstore.persistence.entity.impl.Technique;
import java.io.IOException;
import java.util.List;

/**
 * The {@code TechniqueServiceImpl} class is an implementation of the {@code TechniqueService}
 * interface, providing functionality related to techniques in the application.
 */
public class TechniqueServiceImpl extends GenericService<Technique> implements TechniqueService {

    /** The technique data access object. */
    private TechniqueDAO techniqueDAO;

    /**
     * Constructs a new {@code TechniqueServiceImpl} with the specified technique data access object.
     *
     * @param techniqueDAO The technique data access object.
     */
    public TechniqueServiceImpl(TechniqueDAO techniqueDAO) {
        super(techniqueDAO);
        this.techniqueDAO = techniqueDAO;
    }

    /**
     * Finds all techniques by the specified model.
     *
     * @param model The model to search for.
     * @return A list of techniques with the specified model.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public List<Technique> findAllByModel(String model) throws IOException {
        return techniqueDAO.findAllByModel(model);
    }

    /**
     * Adds a new technique based on the provided technique details.
     *
     * @param techniqueAddDTO The technique details for addition.
     * @return The added technique.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public Technique add(TechniqueAddDTO techniqueAddDTO) throws IOException {
        var technique = new Technique(techniqueAddDTO.getId(), techniqueAddDTO.getPrice(),
                techniqueAddDTO.getCompany(), techniqueAddDTO.getModel());
        techniqueDAO.save(technique);

        return technique;
    }
}
