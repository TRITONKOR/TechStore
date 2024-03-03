package com.tritonkor.techstore.persistence.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tritonkor.techstore.persistence.entity.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonPropertyOrder({"id", "owner", "technique", "text", "grade", "createdAt"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Review extends Entity {

    private final Client owner;
    private final Technique technique;
    private final String text;
    private final Grade grade;
    private final LocalDateTime createdAt;

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

    public Client getOwner() {
        return owner;
    }

    public Technique getTechnique() {return technique;}

    public String getText() {
        return text;
    }

    public Grade getGrade() {
        return grade;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

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
