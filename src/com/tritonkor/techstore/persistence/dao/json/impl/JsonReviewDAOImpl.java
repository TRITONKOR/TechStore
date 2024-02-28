package com.tritonkor.techstore.persistence.dao.json.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tritonkor.techstore.persistence.dao.contracts.ReviewDAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import com.tritonkor.techstore.persistence.entity.impl.Review;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class JsonReviewDAOImpl implements ReviewDAO {

    private final Path filePath;
    private final ObjectMapper objectMapper;

    public JsonReviewDAOImpl() {
        this.filePath = JsonPathFactory.REVIEWS.getPath();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Review save(Review review) throws IOException {
        Set<Review> reviews = findAll();
        reviews.add(review);
        writeDataToFile(reviews);
        return review;
    }

    @Override
    public boolean update(Review review) throws IOException {
        Set<Review> reviews = findAll();
        reviews.removeIf(a -> a.getId() == review.getId());
        reviews.add(review);
        writeDataToFile(reviews);

        return true;
    }

    @Override
    public boolean delete(UUID reviewId) throws IOException {
        Set<Review> reviews = findAll();
        reviews.removeIf(a -> a.getId() == reviewId);
        writeDataToFile(reviews);
        return true;
    }

    @Override
    public Optional<Review> findById(UUID reviewId) throws IOException {
        Set<Review> reviews = findAll();
        return reviews.stream()
                .filter(a -> a.getId() == reviewId)
                .findFirst();
    }

    @Override
    public Set<Review> findAll() throws IOException {
        File file = new File(filePath.toString());
        if (!file.exists()) {
            throw new IOException();
        }

        return objectMapper.readValue(file, new TypeReference<Set<Review>>() {});
    }

    private void writeDataToFile(Set<Review> reviews) throws IOException {
        File file = new File(filePath.toString());
        objectMapper.writeValue(file, reviews);
    }

    @Override
    public Review findByClient(Client client) throws IOException {
        Set<Review> reviews = findAll();
        return reviews.stream()
                .filter(a -> a.getOwner() == client)
                .findFirst().orElse(null);
    }
}
