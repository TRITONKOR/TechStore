package com.tritonkor.techstore.persistence.dao.contracts;

import com.tritonkor.techstore.persistence.DAO;
import com.tritonkor.techstore.persistence.entity.impl.TV;
import java.io.IOException;

public interface TVDAO extends DAO<TV> {

    TV findByModel(String model)  throws IOException;
}
