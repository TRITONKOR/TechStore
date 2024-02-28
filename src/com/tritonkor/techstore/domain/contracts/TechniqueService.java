package com.tritonkor.techstore.domain.contracts;

import com.tritonkor.techstore.domain.Service;
import com.tritonkor.techstore.domain.dto.TechniqueAddDTO;
import com.tritonkor.techstore.persistence.entity.impl.Technique;
import java.io.IOException;
import java.util.Set;

public interface TechniqueService extends Service<Technique> {
    Set<Technique> findAllByModel(String model) throws IOException;

    Technique add(TechniqueAddDTO techniqueAddDTO) throws IOException;
}
