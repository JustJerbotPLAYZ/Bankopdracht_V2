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

	public Bank(String name) {
		this.name = name;
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
		Account newAccount;
		if (type.equalsIgnoreCase("credit")) {
			newAccount = new CreditAccount();
		} else if (type.equalsIgnoreCase("bank")) {
			newAccount = new BankAccount();
		} else if (type.equalsIgnoreCase("account")) {
			newAccount = new Account();
		} else {
			return false;
		}

		newAccount.setBank(this);
		person.setAccount(newAccount);
		accounts.add(newAccount);
		newAccount.save();
		this.save();

		return true;
	}

	public void save() {
		DAOFactory.getFactory().getBankDAO().save(this);
	}

	public void delete() {
		DAOFactory.getFactory().getBankDAO().delete(this);
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
}
