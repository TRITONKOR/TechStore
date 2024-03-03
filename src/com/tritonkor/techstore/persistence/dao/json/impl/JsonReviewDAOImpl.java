package com.tritonkor.techstore.persistence.dao.json.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tritonkor.techstore.persistence.dao.contracts.ReviewDAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import com.tritonkor.techstore.persistence.entity.impl.Review;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class JsonReviewDAOImpl implements ReviewDAO {

    private final Path filePath;
    private final ObjectMapper objectMapper;

    public JsonReviewDAOImpl() {
        this.filePath = JsonPathFactory.REVIEWS.getPath();
        this.objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)
                .registerModule(new JavaTimeModule());
    }

    @Override
    public Review save(Review review) throws IOException {
        List<Review> reviews = findAll();
        reviews.add(review);
        writeDataToFile(reviews);
        return review;
    }

    @Override
    public boolean update(Review review) throws IOException {
        List<Review> reviews = findAll();
        reviews.removeIf(a -> a.getId() == review.getId());
        reviews.add(review);
        writeDataToFile(reviews);

        return true;
    }

    @Override
    public boolean delete(UUID reviewId) throws IOException {
        List<Review> reviews = findAll();
        reviews.removeIf(a -> a.getId() == reviewId);
        writeDataToFile(reviews);
        return true;
    }

    @Override
    public Optional<Review> findById(UUID reviewId) throws IOException {
        List<Review> reviews = findAll();
        return reviews.stream()
                .filter(a -> a.getId() == reviewId)
                .findFirst();
    }

    @Override
    public List<Review> findAll() throws IOException {
        File file = new File(filePath.toString());
        if (!file.exists()) {
            Files.createFile(filePath);
        }
        if (file.length() == 0) {
            return new ArrayList<>();
        }

        return objectMapper.readValue(file, new TypeReference<List<Review>>() {
        });
    }

    private void writeDataToFile(List<Review> reviews) throws IOException {
        File file = new File(filePath.toString());
        objectMapper.writeValue(file, reviews);
    }

    @Override
    public List<Review> findAllByClient(Client client) throws IOException {
        List<Review> reviews = findAll();
        return reviews.stream()
                .filter(a -> a.getOwner().equals(client))
                .toList();
    }
}
