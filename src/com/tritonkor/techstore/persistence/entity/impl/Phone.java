package com.tritonkor.techstore.persistence.entity.impl;

import com.tritonkor.techstore.persistence.entity.Technique;
import java.util.UUID;

public class Phone extends Technique {

    private final int memory;
    private final int ramMemomy;
    private final String resolution;

    public Phone(UUID id, double price, String company, String model, int memory,
            int ramMemomy, String resolution) {
        super(id, price, company, model);
        this.memory = memory;
        this.ramMemomy = ramMemomy;
        this.resolution = resolution;
    }

    public int getMemory() {
        return memory;
    }

    public int getRamMemomy() {
        return ramMemomy;
    }

    public String getResolution() {
        return resolution;
    }
}
