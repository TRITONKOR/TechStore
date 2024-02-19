package com.tritonkor.techstore.persistence.entity.impl;

import com.tritonkor.techstore.persistence.entity.Technique;
import java.util.UUID;

public class TV extends Technique {

    private final int diagonal;
    private final String resolution;
    private final boolean hasSmartTv;

    public TV(UUID id, double price, String company, String model, int diagonal,
            String resolution, boolean hasSmartTv) {
        super(id, price, company, model);
        this.diagonal = diagonal;
        this.resolution = resolution;
        this.hasSmartTv = hasSmartTv;
    }

    public int getDiagonal() {
        return diagonal;
    }

    public String getResolution() {
        return resolution;
    }

    public boolean isHasSmartTv() {
        return hasSmartTv;
    }
}
