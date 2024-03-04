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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the ReviewDAO interface using JSON files for data storage.
 */
public class JsonReviewDAOImpl implements ReviewDAO {

    /** The file path where client data is stored in JSON format. */
    private final Path filePath;

    /** ObjectMapper for JSON serialization and deserialization. */
    private final ObjectMapper objectMapper;

    /**
     * Constructs a new instance of JsonReviewDAOImpl.
     */
    public JsonReviewDAOImpl() {
        this.filePath = JsonPathFactory.REVIEWS.getPath();
        this.objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)
                .registerModule(new JavaTimeModule());
    }

    /**
     * Saves a new review to the JSON file.
     *
     * @param review The review to save.
     * @return The saved review.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public Review save(Review review) throws IOException {
        List<Review> reviews = findAll();
        reviews.add(review);
        writeDataToFile(reviews);
        return review;
    }

    /**
     * Updates an existing review in the JSON file.
     *
     * @param review The review to update.
     * @return True if the update is successful, false otherwise.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public boolean update(Review review) throws IOException {
        List<Review> reviews = findAll();
        reviews.removeIf(a -> a.getId() == review.getId());
        reviews.add(review);
        writeDataToFile(reviews);

        return true;
    }

    /**
     * Deletes a review from the JSON file.
     *
     * @param reviewId The ID of the review to delete.
     * @return True if the deletion is successful, false otherwise.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public boolean delete(UUID reviewId) throws IOException {
        List<Review> reviews = findAll();
        reviews.removeIf(a -> a.getId() == reviewId);
        writeDataToFile(reviews);
        return true;
    }

    /**
     * Finds a review by its ID.
     *
     * @param reviewId The ID of the review to find.
     * @return An Optional containing the found review, or empty if not found.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public Optional<Review> findById(UUID reviewId) throws IOException {
        List<Review> reviews = findAll();
        return reviews.stream()
                .filter(a -> a.getId() == reviewId)
                .findFirst();
    }

    /**
     * Retrieves all reviews from the JSON file.
     *
     * @return A list of all reviews.
     * @throws IOException If an I/O error occurs.
     */
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

    /**
     * Writes a list of reviews to the data store file.
     *
     * @param reviews The list of reviews to be written.
     * @throws IOException If an I/O error occurs during the write operation.
     */
    private void writeDataToFile(List<Review> reviews) throws IOException {
        File file = new File(filePath.toString());
        objectMapper.writeValue(file, reviews);
    }

    /**
     * Retrieves all reviews written by a specific client.
     *
     * @param client The client whose reviews are to be retrieved.
     * @return A list of reviews written by the specified client.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public List<Review> findAllByClient(Client client) throws IOException {
        List<Review> reviews = findAll();
        return reviews.stream()
                .filter(a -> a.getOwner().equals(client))
                .toList();
    }
}
