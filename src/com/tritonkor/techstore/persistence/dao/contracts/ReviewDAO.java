package com.tritonkor.techstore.persistence.dao.contracts;

import com.tritonkor.techstore.persistence.DAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import com.tritonkor.techstore.persistence.entity.impl.Review;
import java.io.IOException;

public interface ReviewDAO extends DAO<Review> {

    Review findByClient(Client client) throws IOException;
}
