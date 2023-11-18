package nl.jeroen.domain.persistence.factories;

import org.hibernate.Session;

import nl.jeroen.domain.Account;
import nl.jeroen.domain.Bank;
import nl.jeroen.domain.persistence.doa.AccountDAO;
import nl.jeroen.domain.persistence.doa.BankDAO;
import nl.jeroen.domain.persistence.doa.GenericDAO;
import nl.jeroen.domain.persistence.interfaces.IAccountDAO;
import nl.jeroen.domain.persistence.interfaces.IBankDAO;
import nl.jeroen.utils.HibernateSessionManager;

public class HibernateDAOFactory extends DAOFactory {

	protected Session getCurrentSession() {
		return HibernateSessionManager.getSessionFactory().getCurrentSession();
	}

	@Override
	public IBankDAO getBankDAO() {
		GenericDAO<Bank, Integer> dao = null;
		try {
			dao = BankDAO.class.newInstance();
			dao.setSession(getCurrentSession());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return (IBankDAO) dao;
	}

	@Override
	public IAccountDAO getAccountDAO() {
		GenericDAO<Account, Integer> dao = null;
		try {
			dao = AccountDAO.class.newInstance();
			dao.setSession(getCurrentSession());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return (IAccountDAO) dao;
	}
}