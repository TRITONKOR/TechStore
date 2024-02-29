package com.tritonkor.techstore.persistence.entity.impl;

import com.tritonkor.techstore.persistence.entity.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

public class Review extends Entity {

    private final Client owner;
    private final String text;
    private final Grade grade;
    private final LocalDateTime createdAt;

    public Review(UUID id, Client owner, String text, Grade grade, LocalDateTime createdAt) {
        super(id);
        this.owner = owner;
        this.text = text;
        this.grade = grade;
        this.createdAt = createdAt;
    }

    public Client getOwner() {
        return owner;
    }

    public String getText() {
        return text;
    }

    public Grade getGrade() {
        return grade;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
