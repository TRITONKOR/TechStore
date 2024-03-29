package com.tritonkor.techstore.persistence.dao.contracts;

import com.tritonkor.techstore.persistence.dao.DAO;
import com.tritonkor.techstore.persistence.entity.impl.Technique;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface TechniqueDAO extends DAO<Technique> {

    List<Technique> findAllByModel(String model) throws IOException;

}
