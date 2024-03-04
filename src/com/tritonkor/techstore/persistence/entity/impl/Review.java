package com.tritonkor.techstore.persistence.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tritonkor.techstore.persistence.entity.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a review entity in the system with a unique identifier, owner, technique, text, grade, and creation timestamp.
 */
@JsonPropertyOrder({"id", "owner", "technique", "text", "grade", "createdAt"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Review extends Entity {

    /** The client who owns the review. */
    private final Client owner;

    /** The technique associated with the review. */
    private final Technique technique;

    /** The text content of the review. */
    private final String text;

    /** The grade assigned to the review. */
    private final Grade grade;

    /** The timestamp when the review was created. */
    private final LocalDateTime createdAt;

    /**
     * Constructs a review with the specified identifier, owner, technique, text, grade, and creation timestamp.
     *
     * @param id         The unique identifier for the review.
     * @param owner      The client who owns the review.
     * @param technique  The technique associated with the review.
     * @param text       The text content of the review.
     * @param grade      The grade assigned to the review.
     * @param createdAt  The timestamp when the review was created.
     */
    public Review(@JsonProperty("id") UUID id, @JsonProperty("owner") Client owner, @JsonProperty("technique") Technique technique,
            @JsonProperty("text") String text, @JsonProperty("grade") Grade grade,
            @JsonProperty("createdAt") LocalDateTime createdAt) {
        super(id);
        this.owner = owner;
        this.technique = technique;
        this.text = text;
        this.grade = grade;
        this.createdAt = createdAt;
    }

    /**
     * Gets the client who owns the review.
     *
     * @return The owner client.
     */
    public Client getOwner() {
        return owner;
    }

    /**
     * Gets the technique associated with the review.
     *
     * @return The associated technique.
     */
    public Technique getTechnique() {return technique;}

    /**
     * Gets the text content of the review.
     *
     * @return The review text.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the grade assigned to the review.
     *
     * @return The assigned grade.
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * Gets the timestamp when the review was created.
     *
     * @return The creation timestamp.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns a string representation of the review.
     *
     * @return A string representation with details of the review.
     */
    @Override
    public String toString() {
        return "Review{" +
                "owner=" + owner.getUsername() +
                ", technique='" + technique.getModel() + '\'' +
                ", text='" + text + '\'' +
                ", grade=" + grade +
                ", createdAt=" + createdAt +
                '}';
    }
}
