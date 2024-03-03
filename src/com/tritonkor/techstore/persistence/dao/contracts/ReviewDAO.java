package com.tritonkor.techstore.persistence.dao.contracts;

import com.tritonkor.techstore.persistence.dao.DAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import com.tritonkor.techstore.persistence.entity.impl.Review;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ReviewDAO extends DAO<Review> {

    List<Review> findAllByClient(Client client) throws IOException;
}
