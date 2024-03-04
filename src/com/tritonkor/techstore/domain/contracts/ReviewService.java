package com.tritonkor.techstore.domain.contracts;

import com.tritonkor.techstore.domain.Service;
import com.tritonkor.techstore.domain.dto.ReviewAddDTO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import com.tritonkor.techstore.persistence.entity.impl.Review;
import java.io.IOException;
import java.util.List;

/**
 * The {@code ReviewService} interface defines the contract for services related to the Review entity.
 * Extends the base Service interface for common CRUD operations.
 */
public interface ReviewService extends Service<Review> {

    /**
     * Retrieves a list of Review entities associated with a specified Client.
     *
     * @param client The Client for which to retrieve reviews.
     * @return A list of Review entities associated with the specified Client.
     * @throws IOException If an I/O error occurs while retrieving data.
     */
    List<Review> findAllByClient(Client client) throws IOException;

    /**
     * Adds a new Review based on the provided ReviewAddDTO.
     *
     * @param reviewAddDTO The DTO containing information to create a new Review.
     * @return The newly added Review entity.
     * @throws IOException If an I/O error occurs while adding the Review.
     */
    Review add(ReviewAddDTO reviewAddDTO) throws IOException;
}
