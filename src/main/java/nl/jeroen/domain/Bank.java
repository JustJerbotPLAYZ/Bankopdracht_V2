package nl.jeroen.domain;

import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import nl.jeroen.domain.persistence.factories.DAOFactory;

@Entity
public class Bank {

	@Id
	@Column(name = "bankname")
	private String name;

	@OneToMany(mappedBy = "bank")
	private SortedSet<Account> accounts = new TreeSet<Account>();

	@Column(name = "accountIdentifier")
	private String accountIdentifier;

	public Bank(String name, String identifier) {
		this.name = name;
		this.accountIdentifier = identifier;
	}

	public boolean transfer(int fromAccountNr, int toAccountNr, double amount) {
		if (getAccountByNr(fromAccountNr).withdraw(amount))
			getAccountByNr(toAccountNr).deposit(amount);
		else
			return false;
		return true;
	}

	private Account getAccountByNr(int accountNr) {
		for (Account acc : accounts) {
			if (acc.getAccountNr().equals(accountNr))
				return acc;
		}
		return null;
	}

	public boolean registerAccount(Person person, String type) {
		for(Account acc: accounts)
			if(acc.getAccountHolder().equals(person)&&acc.getClass().equals(type))
				return false;
		Account newAccount;
		if (type.equalsIgnoreCase("credit")) {
			newAccount = new CreditAccount(this, person);
		} else if (type.equalsIgnoreCase("bank")) {
			newAccount = new BankAccount(this, person);
		} else {
			return false;
		}
		accounts.add(newAccount);
		newAccount.save();
		person.getAccounts().add(newAccount);
		this.save();

		return true;
	}

	public SortedSet<Account> getAccounts() {
		if (accounts.equals(null))
			accounts = new TreeSet<>();
		return accounts;
	}

	public void setAccounts(SortedSet<Account> accounts) {
		this.accounts = accounts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Hibernate methods
	 */

	public void save() {
		DAOFactory.getFactory().getBankDAO().save(this);
	}

	public void delete() {
		DAOFactory.getFactory().getBankDAO().delete(this);
	}

	public void load() {
		DAOFactory.getFactory().getBankDAO().load(this);
	}

	public String getAccountIdentifier() {
		return accountIdentifier;
	}
}
