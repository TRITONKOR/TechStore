package com.tritonkor.techstore.persistence.dao.contracts;

import com.tritonkor.techstore.persistence.dao.DAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import java.io.IOException;
import java.util.Optional;

public interface ClientDAO extends DAO<Client> {

    Optional<Client> findByUsername(String username) throws IOException;

}
