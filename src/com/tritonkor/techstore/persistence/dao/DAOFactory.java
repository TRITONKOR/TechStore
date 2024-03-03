package com.tritonkor.techstore.persistence.dao;

import com.tritonkor.techstore.persistence.dao.contracts.ClientDAO;
import com.tritonkor.techstore.persistence.dao.contracts.ReviewDAO;
import com.tritonkor.techstore.persistence.dao.contracts.TechniqueDAO;
import com.tritonkor.techstore.persistence.dao.json.impl.JsonDAOFactory;

public abstract class DAOFactory {

    public static DAOFactory getDAOFactory() {
        return JsonDAOFactory.getInstance();
    }

    public abstract ClientDAO getClientDAO();
    public abstract ReviewDAO getReviewDAO();
    public abstract TechniqueDAO getTechniqueDAO();
}
