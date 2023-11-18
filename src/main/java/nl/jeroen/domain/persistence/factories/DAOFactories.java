package nl.jeroen.domain.persistence.factories;

public enum DAOFactories {


    HIBERNATE(HibernateDAOFactory.class);

    private final Class<? extends DAOFactory> theFactory;

    private DAOFactories(Class<? extends DAOFactory> factory) {
        this.theFactory = factory;
    }

    public Class<? extends DAOFactory> getTheFactory() {
        return theFactory;
    }


}