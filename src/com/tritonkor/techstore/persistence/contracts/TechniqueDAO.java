package com.tritonkor.techstore.persistence.contracts;

import com.tritonkor.techstore.persistence.DAO;
import com.tritonkor.techstore.persistence.entity.Technique;
import java.io.IOException;

public interface TechniqueDAO extends DAO<Technique> {

    Technique findByModel(String model) throws IOException;
}
