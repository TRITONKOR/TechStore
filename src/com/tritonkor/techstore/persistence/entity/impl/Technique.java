package com.tritonkor.techstore.persistence.entity.impl;

import com.tritonkor.techstore.persistence.entity.Entity;
import java.util.UUID;

public class Technique extends Entity {

    private double price;
    private final String company;
    private final String model;

    public Technique(UUID id, double price, String company, String model) {
        super(id);
        this.price = price;
        this.company = company;
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public String getModel() {
        return model;
    }
}
