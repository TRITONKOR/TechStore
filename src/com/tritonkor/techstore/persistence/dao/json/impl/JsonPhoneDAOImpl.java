package com.tritonkor.techstore.persistence.dao.json.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tritonkor.techstore.persistence.dao.contracts.PhoneDAO;
import com.tritonkor.techstore.persistence.entity.impl.Phone;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class JsonPhoneDAOImpl implements PhoneDAO {

    private final String filePath;
    private final ObjectMapper objectMapper;

    public JsonPhoneDAOImpl(String filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Phone save(Phone phone) throws IOException {
        Set<Phone> phones = findAll();
        phones.add(phone);
        writeDataToFile(phones);
        return phone;
    }

    @Override
    public boolean update(Phone phone) throws IOException {
        Set<Phone> phones = findAll();
        phones.removeIf(a -> a.getId() == phone.getId());
        phones.add(phone);
        writeDataToFile(phones);

        return true;
    }

    @Override
    public boolean delete(UUID phoneId) throws IOException {
        Set<Phone> phones = findAll();
        phones.removeIf(a -> a.getId() == phoneId);
        writeDataToFile(phones);
        return true;
    }

    @Override
    public Optional<Phone> findById(UUID phoneId) throws IOException {
        Set<Phone> phones = findAll();
        return phones.stream()
                .filter(a -> a.getId() == phoneId)
                .findFirst();
    }

    @Override
    public Set<Phone> findAll() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException();
        }

        return objectMapper.readValue(file, new TypeReference<Set<Phone>>() {});
    }

    private void writeDataToFile(Set<Phone> phones) throws IOException {
        File file = new File(filePath);
        objectMapper.writeValue(file, phones);
    }

    @Override
    public Phone findByModel(String model) throws IOException {
        Set<Phone> phones = findAll();
        return phones.stream()
                .filter(a -> a.getModel() == model)
                .findFirst().orElse(null);
    }
}
