package com.tritonkor.techstore.persistence.dao.json.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tritonkor.techstore.persistence.dao.contracts.LaptopDAO;
import com.tritonkor.techstore.persistence.entity.impl.Laptop;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class JsonLaptopDAOImpl implements LaptopDAO {

    private final String filePath;
    private final ObjectMapper objectMapper;

    public JsonLaptopDAOImpl(String filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Laptop save(Laptop laptop) throws IOException {
        Set<Laptop> laptops = findAll();
        laptops.add(laptop);
        writeDataToFile(laptops);
        return laptop;
    }

    @Override
    public boolean update(Laptop laptop) throws IOException {
        Set<Laptop> laptops = findAll();
        laptops.removeIf(a -> a.getId() == laptop.getId());
        laptops.add(laptop);
        writeDataToFile(laptops);

        return true;
    }

    @Override
    public boolean delete(UUID laptopId) throws IOException {
        Set<Laptop> laptops = findAll();
        laptops.removeIf(a -> a.getId() == laptopId);
        writeDataToFile(laptops);
        return true;
    }

    @Override
    public Optional<Laptop> findById(UUID laptopId) throws IOException {
        Set<Laptop> laptops = findAll();
        return laptops.stream()
                .filter(a -> a.getId() == laptopId)
                .findFirst();
    }

    @Override
    public Set<Laptop> findAll() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException();
        }

        return objectMapper.readValue(file, new TypeReference<Set<Laptop>>() {});
    }

    private void writeDataToFile(Set<Laptop> laptops) throws IOException {
        File file = new File(filePath);
        objectMapper.writeValue(file, laptops);
    }

    @Override
    public Laptop findByModel(String model) throws IOException {
        Set<Laptop> laptops = findAll();
        return laptops.stream()
                .filter(a -> a.getModel() == model)
                .findFirst().orElse(null);
    }
}
