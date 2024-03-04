package com.tritonkor.techstore.persistence.dao;

import com.tritonkor.techstore.persistence.dao.contracts.ClientDAO;
import com.tritonkor.techstore.persistence.dao.contracts.ReviewDAO;
import com.tritonkor.techstore.persistence.dao.contracts.TechniqueDAO;
import com.tritonkor.techstore.persistence.dao.json.impl.JsonDAOFactory;

/**
 * Abstract factory for creating Data Access Object (DAO) instances. Provides a method to obtain
 * an instance of the factory and methods to create specific DAO instances for clients, reviews, and techniques.
 */
public abstract class DAOFactory {

    /**
     * Gets the appropriate DAO factory instance. Currently, only a JsonDAOFactory is implemented.
     *
     * @return An instance of the DAOFactory.
     */
    public static DAOFactory getDAOFactory() {
        return JsonDAOFactory.getInstance();
    }

    /**
     * Creates a ClientDAO instance.
     *
     * @return An instance of the ClientDAO.
     */
    public abstract ClientDAO getClientDAO();

    /**
     * Creates a ReviewDAO instance.
     *
     * @return An instance of the ReviewDAO.
     */
    public abstract ReviewDAO getReviewDAO();

    /**
     * Creates a TechniqueDAO instance.
     *
     * @return An instance of the TechniqueDAO.
     */
    public abstract TechniqueDAO getTechniqueDAO();
}
