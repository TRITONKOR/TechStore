package com.tritonkor.techstore.persistence.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tritonkor.techstore.persistence.entity.Entity;
import java.util.UUID;

@JsonPropertyOrder({"id", "price", "company", "model"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Technique extends Entity {

    private double price;
    private final String company;
    private final String model;

    public Technique(@JsonProperty("id") UUID id, @JsonProperty("price") double price,
            @JsonProperty("company") String company, @JsonProperty("model") String model) {
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

    @Override
    public String toString() {
        return "Technique{" +
                "price=" + price +
                ", company='" + company + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
