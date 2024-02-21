package com.tritonkor.techstore.persistence.dao.contracts;

import com.tritonkor.techstore.persistence.DAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import java.io.IOException;

public interface ClientDAO extends DAO<Client> {

    Client findByUsername(String username) throws IOException;

}
