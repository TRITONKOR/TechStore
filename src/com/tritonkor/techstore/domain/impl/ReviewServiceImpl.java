package com.tritonkor.techstore.domain.impl;

import com.tritonkor.techstore.domain.contracts.ReviewService;
import com.tritonkor.techstore.domain.dto.ReviewAddDTO;
import com.tritonkor.techstore.persistence.dao.contracts.ReviewDAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import com.tritonkor.techstore.persistence.entity.impl.Review;
import java.io.IOException;
import java.util.List;

/**
 * The {@code ReviewServiceImpl} class is an implementation of the {@code ReviewService} interface,
 * providing review-related functionality in the application.
 */
public class ReviewServiceImpl extends GenericService<Review> implements ReviewService {

    /** The data access object for review entities. */
    private ReviewDAO reviewDAO;

    /**
     * Constructs a new {@code ReviewServiceImpl} with the specified review data access object.
     *
     * @param reviewDAO The review data access object.
     */
    public ReviewServiceImpl(ReviewDAO reviewDAO) {
        super(reviewDAO);
        this.reviewDAO = reviewDAO;
    }

    /**
     * Retrieves all reviews associated with a specific client.
     *
     * @param client The client for which to retrieve reviews.
     * @return A list of reviews associated with the client.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public List<Review> findAllByClient(Client client) throws IOException {
        return reviewDAO.findAllByClient(client);
    }

    /**
     * Adds a new review to the system based on the provided review data transfer object.
     *
     * @param reviewAddDTO The review data transfer object.
     * @return The added review.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public Review add(ReviewAddDTO reviewAddDTO) throws IOException {
        var review = new Review(reviewAddDTO.getId(), reviewAddDTO.getOwner(),
                reviewAddDTO.getTechnique(),
                reviewAddDTO.getText(), reviewAddDTO.getGrade(), reviewAddDTO.getCreatedAt());

        reviewDAO.save(review);
        return review;
    }
}
