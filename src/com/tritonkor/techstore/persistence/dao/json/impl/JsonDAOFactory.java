package com.tritonkor.techstore.persistence.dao.json.impl;

public class JsonDAOFactory {

    private JsonTechniqueDAOImpl jsonTechniqueDAO;
    private JsonReviewDAOImpl jsonReviewDAO;
    private JsonClientDAOImpl jsonClientDAO;

    private JsonDAOFactory() {

        jsonClientDAO = new JsonClientDAOImpl();
        jsonClientDAO = new JsonClientDAOImpl();
        jsonReviewDAO = new JsonReviewDAOImpl();
    }

    public JsonTechniqueDAOImpl getJsonTechniqueDAO() {
        return jsonTechniqueDAO;
    }

    public JsonReviewDAOImpl getJsonReviewDAO() {
        return jsonReviewDAO;
    }

    public JsonClientDAOImpl getJsonClientDAO() {
        return jsonClientDAO;
    }

    public static JsonDAOFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {

        public static final JsonDAOFactory INSTANCE = new JsonDAOFactory();
    }
}
