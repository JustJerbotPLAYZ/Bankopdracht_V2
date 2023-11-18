package nl.jeroen.domain.persistence.factories;

import nl.jeroen.domain.persistence.interfaces.IAccountDAO;
import nl.jeroen.domain.persistence.interfaces.IBankDAO;
import nl.jeroen.domain.persistence.interfaces.IPersonDAO;

public abstract class DAOFactory {

    private static DAOFactory factory;

    public static DAOFactory getFactory() {
        return factory;
    }

    public static void setFactory(Class<? extends DAOFactory> factory) {
        try {
            DAOFactory.factory = factory.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to create DAOFactory: " + factory);
        }
    }

    public abstract IBankDAO getBankDAO();
    public abstract IAccountDAO getAccountDAO();
    public abstract IPersonDAO getPersonDAO();

}