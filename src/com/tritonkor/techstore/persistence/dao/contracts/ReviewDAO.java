package com.tritonkor.techstore.persistence.dao.contracts;

import com.tritonkor.techstore.persistence.dao.DAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import com.tritonkor.techstore.persistence.entity.impl.Review;
import java.io.IOException;
import java.util.List;

/**
 * Data Access Object (DAO) interface for managing Review entities. Extends the generic DAO interface.
 */
public interface ReviewDAO extends DAO<Review> {

    /**
     * Finds all reviews associated with a specific client.
     *
     * @param client The client for whom to retrieve reviews.
     * @return A List containing all reviews associated with the specified client.
     * @throws IOException If there is an issue accessing the data store.
     */
    List<Review> findAllByClient(Client client) throws IOException;
}
