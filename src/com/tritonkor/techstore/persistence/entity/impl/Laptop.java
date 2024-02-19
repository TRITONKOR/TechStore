package com.tritonkor.techstore.persistence.entity.impl;

import com.tritonkor.techstore.persistence.entity.Technique;
import java.util.UUID;

public class Laptop extends Technique {

    private final int diagonal;
    private final String resolution;
    private final int memory;
    private final int ramMemory;
    private final String processor;

    public Laptop(UUID id, double price, String company, String model, int diagonal,
            String resolution, int memory, int ramMemory, String processor) {
        super(id, price, company, model);
        this.diagonal = diagonal;
        this.resolution = resolution;
        this.memory = memory;
        this.ramMemory = ramMemory;
        this.processor = processor;
    }

    public int getDiagonal() {
        return diagonal;
    }

    public String getResolution() {
        return resolution;
    }

    public int getMemory() {
        return memory;
    }

    public int getRamMemory() {
        return ramMemory;
    }

    public String getProcessor() {
        return processor;
    }
}
