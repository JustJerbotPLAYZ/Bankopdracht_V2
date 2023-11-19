package nl.jeroen.starter;

import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.Transaction;

import nl.jeroen.domain.Account;
import nl.jeroen.domain.Bank;
import nl.jeroen.domain.Person;
import nl.jeroen.domain.persistence.factories.DAOFactories;
import nl.jeroen.domain.persistence.factories.DAOFactory;
import nl.jeroen.utils.HibernateSessionManager;

public class Starter {
	public static void main(String[] args) {
		DAOFactory.setFactory(DAOFactories.HIBERNATE.getTheFactory());
	    Session session = HibernateSessionManager.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		
		Bank ABN = new Bank("ABN Amro", "ABNA");
		Person jeroen = new Person("Jeroen Suurmond","JSUU1203732849",18);
		jeroen.load();
		ABN.load();
		
		ABN.registerAccount(jeroen, "bank");
		ABN.registerAccount(jeroen, "bank");
		ABN.registerAccount(jeroen, "bank");
		ABN.registerAccount(jeroen, "bank");
		ABN.registerAccount(jeroen, "bank");
		ABN.registerAccount(jeroen, "bank");
		ABN.registerAccount(jeroen, "bank");
		ABN.registerAccount(jeroen, "bank");
		ABN.registerAccount(jeroen, "bank");
		ABN.registerAccount(jeroen, "bank");
		ABN.registerAccount(jeroen, "bank");
		ABN.registerAccount(jeroen, "bank");
		ABN.registerAccount(jeroen, "bank");
		
		
		
		jeroen.save();
		ABN.save();
	    trans.commit();
	    session.close();
	}
}
