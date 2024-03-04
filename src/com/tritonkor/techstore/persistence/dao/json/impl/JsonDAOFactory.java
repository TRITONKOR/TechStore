package com.tritonkor.techstore.persistence.dao.json.impl;

import com.tritonkor.techstore.persistence.dao.DAOFactory;
import com.tritonkor.techstore.persistence.dao.contracts.ClientDAO;
import com.tritonkor.techstore.persistence.dao.contracts.ReviewDAO;
import com.tritonkor.techstore.persistence.dao.contracts.TechniqueDAO;

/**
 * Factory class for creating JSON-based DAO instances.
 */
public class JsonDAOFactory extends DAOFactory {

    /** The instance of JsonTechniqueDAOImpl. */
    private final JsonTechniqueDAOImpl jsonTechniqueDAOImpl;

    /** The instance of JsonReviewDAOImpl. */
    private final JsonReviewDAOImpl jsonReviewDAOImpl;

    /** The instance of JsonClientDAOImpl. */
    private final JsonClientDAOImpl jsonClientDAOImpl;

    /**
     * Constructs a new instance of JsonDAOFactory.
     */
    private JsonDAOFactory() {

        jsonClientDAOImpl = new JsonClientDAOImpl();
        jsonTechniqueDAOImpl = new JsonTechniqueDAOImpl();
        jsonReviewDAOImpl = new JsonReviewDAOImpl();
    }

    /**
     * Gets the instance of ClientDAO.
     *
     * @return The instance of ClientDAO.
     */
    @Override
    public ClientDAO getClientDAO() {
        return jsonClientDAOImpl;
    }

    /**
     * Gets the instance of ReviewDAO.
     *
     * @return The instance of ReviewDAO.
     */
    @Override
    public ReviewDAO getReviewDAO() {
        return jsonReviewDAOImpl;
    }

    /**
     * Gets the instance of TechniqueDAO.
     *
     * @return The instance of TechniqueDAO.
     */
    @Override
    public TechniqueDAO getTechniqueDAO() {
        return jsonTechniqueDAOImpl;
    }

    /**
     * Gets the instance of JsonDAOFactory (Singleton pattern).
     *
     * @return The instance of JsonDAOFactory.
     */
    public static JsonDAOFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * Holder class for the Singleton pattern.
     */
    private static class InstanceHolder {

        /** The Singleton instance of JsonDAOFactory. */
        public static final JsonDAOFactory INSTANCE = new JsonDAOFactory();
    }
}
