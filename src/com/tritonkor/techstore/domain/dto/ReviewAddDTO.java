package com.tritonkor.techstore.domain.dto;

import com.tritonkor.techstore.persistence.entity.Entity;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import com.tritonkor.techstore.persistence.entity.impl.Grade;
import com.tritonkor.techstore.persistence.entity.impl.Technique;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReviewAddDTO extends Entity {

    private final Client owner;
    private final Technique technique;
    private final String text;
    private final Grade grade;
    private final LocalDateTime createdAt;

    public ReviewAddDTO(UUID id, Client owner, Technique technique, String text, Grade grade,
            LocalDateTime createdAt) {
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

    public Technique getTechnique() {
        return technique;
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
