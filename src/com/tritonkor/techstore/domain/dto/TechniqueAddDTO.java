package com.tritonkor.techstore.domain.dto;

import com.tritonkor.techstore.persistence.entity.Entity;
import java.util.UUID;

public class TechniqueAddDTO extends Entity {

    private double price;
    private final String company;
    private final String model;

    public TechniqueAddDTO(UUID id, double price, String company, String model) {
        super(id);
        this.price = price;
        this.company = company;
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public String getCompany() {
        return company;
    }

    public String getModel() {
        return model;
    }
}
