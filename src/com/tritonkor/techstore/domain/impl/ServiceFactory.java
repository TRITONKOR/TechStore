package com.tritonkor.techstore.domain.impl;

import com.tritonkor.techstore.domain.contracts.AuthService;
import com.tritonkor.techstore.domain.contracts.ClientService;
import com.tritonkor.techstore.domain.contracts.ReviewService;
import com.tritonkor.techstore.domain.contracts.SignUpService;
import com.tritonkor.techstore.domain.contracts.TechniqueService;
import com.tritonkor.techstore.domain.exception.DependencyException;
import com.tritonkor.techstore.persistence.dao.DAOFactory;

/**
 * The {@code ServiceFactory} class provides instances of various services used in the application.
 */
public final class ServiceFactory {

    /** The singleton instance of the ServiceFactory. */
    private static volatile ServiceFactory INSTANCE;

    /** The authentication service instance. */
    private final AuthService authService;

    /** The sign-up service instance. */
    private final SignUpService signUpService;

    /** The client service instance. */
    private final ClientService clientService;

    /** The review service instance. */
    private final ReviewService reviewService;

    /** The technique service instance. */
    private final TechniqueService techniqueService;

    /** The DAO factory instance. */
    private final DAOFactory daoFactory;

    /**
     * Constructs a new ServiceFactory with the specified DAOFactory.
     *
     * @param daoFactory The DAO factory to be used for creating DAO instances.
     */
    public ServiceFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;

        authService = new AuthServiceImpl(daoFactory.getClientDAO());
        clientService = new ClientServiceImpl(daoFactory.getClientDAO());
        signUpService = new SignUpServiceImpl(clientService, daoFactory.getClientDAO());
        techniqueService = new TechniqueServiceImpl(daoFactory.getTechniqueDAO());
        reviewService = new ReviewServiceImpl(daoFactory.getReviewDAO());
    }

    /**
     * Gets the singleton instance of ServiceFactory. Throws DependencyException if DAOFactory is not created.
     *
     * @return The singleton instance of ServiceFactory.
     */
    public static ServiceFactory getInstance() {
        if (INSTANCE.daoFactory != null) {
            return INSTANCE;
        } else {
            throw new DependencyException(
                    "You forgot to create the DAOFactory object before using the ServiceFactory.");
        }
    }

    /**
     * Gets the singleton instance of ServiceFactory. Creates a new instance if not already created.
     *
     * @param daoFactory The DAOFactory to be used for creating DAO instances.
     * @return The singleton instance of ServiceFactory.
     */
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

    /**
     * Gets the authentication service instance.
     *
     * @return The authentication service instance.
     */
    public AuthService getAuthService() {
        return authService;
    }

    /**
     * Gets the sign-up service instance.
     *
     * @return The sign-up service instance.
     */
    public SignUpService getSignUpService() {
        return signUpService;
    }

    /**
     * Gets the client service instance.
     *
     * @return The client service instance.
     */
    public ClientService getClientService() {
        return clientService;
    }

    /**
     * Gets the review service instance.
     *
     * @return The review service instance.
     */
    public ReviewService getReviewService() {
        return reviewService;
    }

    /**
     * Gets the technique service instance.
     *
     * @return The technique service instance.
     */
    public TechniqueService getTechniqueService() {
        return techniqueService;
    }

    /**
     * Gets the DAO factory instance.
     *
     * @return The DAO factory instance.
     */
    public DAOFactory getDaoFactory() {
        return daoFactory;
    }
}
