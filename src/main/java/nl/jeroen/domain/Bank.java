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

	@Column(name = "nextAccountNr")
	private int nextAccountNr = 1000;

	public Bank(String name, String identifier) {
		this.name = name;
		this.accountIdentifier = identifier;
	}

	public boolean transfer(String fromAccountNr, String toAccountNr, double amount) {
		if (getAccountByNr(fromAccountNr).withdraw(amount))
			getAccountByNr(toAccountNr).deposit(amount);
		else
			return false;
		return true;
	}

	private Account getAccountByNr(String accountNr) {
		for (Account acc : accounts) {
			if (acc.getAccountNr().equals(accountNr))
				return acc;
		}
		return null;
	}

	public boolean registerAccount(Person person, String type) {
		Account newAccount;
		switch (type.toLowerCase()) {
		case "bank":
			BankAccount temp = new BankAccount(this, person);
			newAccount = temp;
			break;
		case "credit":
			CreditAccount temp1 = new CreditAccount(this, person);
			newAccount = temp1;
			break;
		default:
			return false;
		}
		for (Account acc : accounts)
			if (person.equals(acc.getAccountHolder()) && newAccount.getClass().equals(acc.getClass()))
				return false;
		newAccount.assignAccountNr();
		accounts.add(newAccount);
		newAccount.save();
		person.getAccounts().add(newAccount);
		this.save();
		System.out.println(type + "account created");
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

	public void setAccounts(TreeSet<Account> accounts) {
		this.accounts = accounts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNextAccountNr() {
		return nextAccountNr;
	}

	public void setNextAccountNr(int nextAccountNr) {
		this.nextAccountNr = nextAccountNr;
	}

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
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
}
