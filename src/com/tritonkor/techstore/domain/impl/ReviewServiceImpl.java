package com.tritonkor.techstore.domain.impl;

import com.tritonkor.techstore.domain.contracts.ReviewService;
import com.tritonkor.techstore.domain.dto.ReviewAddDTO;
import com.tritonkor.techstore.persistence.dao.contracts.ReviewDAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import com.tritonkor.techstore.persistence.entity.impl.Review;
import java.io.IOException;
import java.util.List;

public class ReviewServiceImpl extends GenericService<Review> implements ReviewService {

    private ReviewDAO reviewDAO;

    public ReviewServiceImpl(ReviewDAO reviewDAO) {
        super(reviewDAO);
        this.reviewDAO = reviewDAO;
    }

    @Override
    public List<Review> findAllByClient(Client client) throws IOException {
        return reviewDAO.findAllByClient(client);
    }

    @Override
    public Review add(ReviewAddDTO reviewAddDTO) throws IOException {
        var review = new Review(reviewAddDTO.getId(), reviewAddDTO.getOwner(),
                reviewAddDTO.getTechnique(),
                reviewAddDTO.getText(), reviewAddDTO.getGrade(), reviewAddDTO.getCreatedAt());

        reviewDAO.save(review);
        return review;
    }
}
