package com.tritonkor.techstore.domain.dto;

import com.tritonkor.techstore.persistence.entity.Entity;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import com.tritonkor.techstore.persistence.entity.impl.Grade;
import java.time.LocalDate;
import java.util.UUID;

public class ReviewAddDTO extends Entity {

    private final Client owner;
    private final String text;
    private final Grade grade;
    private final LocalDate createdAt;

    public ReviewAddDTO(UUID id, Client owner, String text, Grade grade,
            LocalDate createdAt) {
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}