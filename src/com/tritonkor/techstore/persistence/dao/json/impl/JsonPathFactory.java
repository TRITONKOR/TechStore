package com.tritonkor.techstore.persistence.dao.json.impl;

import java.nio.file.Path;

/**
 * The {@code JsonPathFactory} enum provides paths for JSON files used in the dao.
 */
public enum JsonPathFactory {

    CLIENTS("clients.json"),
    TVS("tvs.json"),
    PHONES("phones.json"),
    LAPTOPS("laptops.json"),
    REVIEWS("reviews.json"),
    DATA("data");;

    private final String fileName;
    private static final String DATA_DIRECTORY = "data";

    JsonPathFactory(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the full path to the JSON file.
     *
     * @return The full path to the JSON file.
     */
    public Path getPath() {
        return Path.of(DATA_DIRECTORY.toString(), this.fileName);
    }

    /**
     * Gets the path to the data directory.
     *
     * @return The path to the data directory.
     */
    public Path getDataPath() {
        return Path.of(DATA_DIRECTORY);
    }
}
