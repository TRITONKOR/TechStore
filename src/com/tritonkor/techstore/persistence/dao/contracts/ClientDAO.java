package com.tritonkor.techstore.persistence.dao.contracts;

import com.tritonkor.techstore.persistence.dao.DAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import java.io.IOException;
import java.util.Optional;

/**
 * Data Access Object (DAO) interface for managing Client entities. Extends the generic DAO interface.
 */
public interface ClientDAO extends DAO<Client> {

    /**
     * Finds a client by their username.
     *
     * @param username The username of the client to find.
     * @return An Optional containing the found client, or an empty Optional if not found.
     * @throws IOException If there is an issue accessing the data store.
     */
    Optional<Client> findByUsername(String username) throws IOException;

}
