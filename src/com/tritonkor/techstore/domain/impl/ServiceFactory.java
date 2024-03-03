package com.tritonkor.techstore.domain.impl;

import com.tritonkor.techstore.domain.contracts.AuthService;
import com.tritonkor.techstore.domain.contracts.ClientService;
import com.tritonkor.techstore.domain.contracts.ReviewService;
import com.tritonkor.techstore.domain.contracts.SignUpService;
import com.tritonkor.techstore.domain.contracts.TechniqueService;
import com.tritonkor.techstore.domain.exception.DependencyException;
import com.tritonkor.techstore.persistence.dao.DAOFactory;

public final class ServiceFactory {

    private static volatile ServiceFactory INSTANCE;

    private final AuthService authService;
    private final SignUpService signUpService;
    private final ClientService clientService;
    private final ReviewService reviewService;
    private final TechniqueService techniqueService;
    private final DAOFactory daoFactory;

    public ServiceFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;

        authService = new AuthServiceImpl(daoFactory.getClientDAO());
        clientService = new ClientServiceImpl(daoFactory.getClientDAO());
        signUpService = new SignUpServiceImpl(clientService, daoFactory.getClientDAO());
        techniqueService = new TechniqueServiceImpl(daoFactory.getTechniqueDAO());
        reviewService = new ReviewServiceImpl(daoFactory.getReviewDAO());
    }

    public static ServiceFactory getInstance() {
        if (INSTANCE.daoFactory != null) {
            return INSTANCE;
        } else {
            throw new DependencyException(
                    "You forgot to create the DAOFactory object before using the ServiceFactory.");
        }
    }

    public static ServiceFactory getInstance(DAOFactory daoFactory) {
        if (INSTANCE == null) {
            synchronized (ServiceFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceFactory(daoFactory);
                }
            }
        }

        return INSTANCE;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public SignUpService getSignUpService() {
        return signUpService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }

    public TechniqueService getTechniqueService() {
        return techniqueService;
    }

    public DAOFactory getDaoFactory() {
        return daoFactory;
    }
}
