package com.tritonkor.techstore.domain.dto;

import com.tritonkor.techstore.persistence.entity.Entity;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import com.tritonkor.techstore.persistence.entity.impl.Grade;
import com.tritonkor.techstore.persistence.entity.impl.Technique;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The {@code ReviewAddDTO} class represents a Data Transfer Object (DTO) for creating a new Review.
 * Extends the base Entity class to inherit the common identifier field.
 */
public class ReviewAddDTO extends Entity {

    /** The owner (Client) of the review. */
    private final Client owner;

    /** The technique being reviewed. */
    private final Technique technique;

    /** The text content of the review. */
    private final String text;

    /** The grade assigned to the review. */
    private final Grade grade;

    /** The creation timestamp of the review. */
    private final LocalDateTime createdAt;

    /**
     * Constructs a new instance of ReviewAddDTO.
     *
     * @param id The unique identifier for the DTO.
     * @param owner The owner (Client) of the review.
     * @param technique The technique being reviewed.
     * @param text The text content of the review.
     * @param grade The grade assigned to the review.
     * @param createdAt The creation timestamp of the review.
     */
    public ReviewAddDTO(UUID id, Client owner, Technique technique, String text, Grade grade,
            LocalDateTime createdAt) {
        super(id);
        this.owner = owner;
        this.technique = technique;
        this.text = text;
        this.grade = grade;
        this.createdAt = createdAt;
    }

    /**
     * Gets the owner (Client) of the review.
     *
     * @return The owner.
     */
    public Client getOwner() {
        return owner;
    }

    /**
     * Gets the technique being reviewed.
     *
     * @return The technique.
     */
    public Technique getTechnique() {
        return technique;
    }

    /**
     * Gets the text content of the review.
     *
     * @return The text content.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the grade assigned to the review.
     *
     * @return The grade.
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * Gets the creation timestamp of the review.
     *
     * @return The creation timestamp.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
