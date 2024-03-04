package com.tritonkor.techstore.persistence.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tritonkor.techstore.persistence.entity.Entity;
import java.util.UUID;

/**
 * Represents a technique entity in the system with a unique identifier, price, company, and model.
 */
@JsonPropertyOrder({"id", "price", "company", "model"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Technique extends Entity {

    /** The price of the technique. */
    private double price;

    /** The company producing the technique. */
    private final String company;

    /** The model name of the technique. */
    private final String model;

    /**
     * Constructs a technique with the specified identifier, price, company, and model.
     *
     * @param id      The unique identifier for the technique.
     * @param price   The price of the technique.
     * @param company The company producing the technique.
     * @param model   The model name of the technique.
     */
    public Technique(@JsonProperty("id") UUID id, @JsonProperty("price") double price,
            @JsonProperty("company") String company, @JsonProperty("model") String model) {
        super(id);
        this.price = price;
        this.company = company;
        this.model = model;
    }

    /**
     * Gets the price of the technique.
     *
     * @return The technique's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the technique.
     *
     * @param price The new price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the company producing the technique.
     *
     * @return The producing company.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Gets the model name of the technique.
     *
     * @return The model name.
     */
    public String getModel() {
        return model;
    }

    /**
     * Returns a string representation of the technique.
     *
     * @return A string representation in the format "Technique{price=..., company='...', model='...'}".
     */
    @Override
    public String toString() {
        return "Technique{" +
                "price=" + price +
                ", company='" + company + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
