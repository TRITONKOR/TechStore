package com.tritonkor.techstore.persistence.dao.contracts;

import com.tritonkor.techstore.persistence.DAO;
import com.tritonkor.techstore.persistence.entity.impl.Phone;
import java.io.IOException;

public interface PhoneDAO extends DAO<Phone> {

    Phone findByModel(String model)  throws IOException;
}
