package com.tritonkor.techstore.persistence.dao.contracts;

import com.tritonkor.techstore.persistence.DAO;
import com.tritonkor.techstore.persistence.entity.impl.Laptop;
import java.io.IOException;

public interface LaptopDAO extends DAO<Laptop> {

    Laptop findByModel(String model)  throws IOException;
}
