package com.tritonkor.techstore.domain.dto;

import com.tritonkor.techstore.persistence.entity.Entity;
import java.util.UUID;

/**
 * The {@code TechniqueAddDTO} class represents a Data Transfer Object (DTO) for adding a new Technique.
 * Extends the base Entity class to inherit the common identifier field.
 */
public class TechniqueAddDTO extends Entity {

    /** The price of the new technique. */
    private double price;

    /** The company of the new technique. */
    private final String company;

    /** The model of the new technique. */
    private final String model;

    /**
     * Constructs a new instance of TechniqueAddDTO.
     *
     * @param id The unique identifier for the DTO.
     * @param price The price of the new technique.
     * @param company The company of the new technique.
     * @param model The model of the new technique.
     */
    public TechniqueAddDTO(UUID id, double price, String company, String model) {
        super(id);
        this.price = price;
        this.company = company;
        this.model = model;
    }

    /**
     * Gets the price of the new technique.
     *
     * @return The price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the company of the new technique.
     *
     * @return The company.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Gets the model of the new technique.
     *
     * @return The model.
     */
    public String getModel() {
        return model;
    }
}
