package nl.jeroen.starter;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
		ABN.save();
		Person jeroen = new Person("Johannes Daniël Suurmond","JSUU1203732849",18);
		jeroen.save();
		
		ABN.registerAccount(jeroen, "credit");
		System.out.println(jeroen.getAccounts());
		
	    trans.commit();
	    session.close();
	}
}
