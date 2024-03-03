package com.tritonkor.techstore.domain.impl;

import com.tritonkor.techstore.domain.contracts.TechniqueService;
import com.tritonkor.techstore.domain.dto.TechniqueAddDTO;
import com.tritonkor.techstore.persistence.dao.DAO;
import com.tritonkor.techstore.persistence.dao.contracts.TechniqueDAO;
import com.tritonkor.techstore.persistence.entity.impl.Technique;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class TechniqueServiceImpl extends GenericService<Technique> implements TechniqueService {

    private TechniqueDAO techniqueDAO;

    public TechniqueServiceImpl(TechniqueDAO techniqueDAO) {
        super(techniqueDAO);
        this.techniqueDAO = techniqueDAO;
    }

    @Override
    public List<Technique> findAllByModel(String model) throws IOException {
        return techniqueDAO.findAllByModel(model);
    }

    @Override
    public Technique add(TechniqueAddDTO techniqueAddDTO) throws IOException {
        var technique = new Technique(techniqueAddDTO.getId(), techniqueAddDTO.getPrice(),
                techniqueAddDTO.getCompany(), techniqueAddDTO.getModel());
        techniqueDAO.save(technique);

        return technique;
    }
}
