package com.tritonkor.techstore.persistence.dao.json.impl;

import com.tritonkor.techstore.persistence.dao.DAOFactory;
import com.tritonkor.techstore.persistence.dao.contracts.ClientDAO;
import com.tritonkor.techstore.persistence.dao.contracts.ReviewDAO;
import com.tritonkor.techstore.persistence.dao.contracts.TechniqueDAO;

public class JsonDAOFactory extends DAOFactory {

    private JsonTechniqueDAOImpl jsonTechniqueDAOImpl;
    private JsonReviewDAOImpl jsonReviewDAOImpl;
    private JsonClientDAOImpl jsonClientDAOImpl;

    private JsonDAOFactory() {

        jsonClientDAOImpl = new JsonClientDAOImpl();
        jsonTechniqueDAOImpl = new JsonTechniqueDAOImpl();
        jsonReviewDAOImpl = new JsonReviewDAOImpl();
    }

    @Override
    public ClientDAO getClientDAO() {
        return jsonClientDAOImpl;
    }

    @Override
    public ReviewDAO getReviewDAO() {
        return jsonReviewDAOImpl;
    }

    @Override
    public TechniqueDAO getTechniqueDAO() {
        return jsonTechniqueDAOImpl;
    }

    public static JsonDAOFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {

        public static final JsonDAOFactory INSTANCE = new JsonDAOFactory();
    }
}
