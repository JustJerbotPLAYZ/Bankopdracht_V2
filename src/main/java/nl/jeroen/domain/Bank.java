package nl.jeroen.domain;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank")
public class Bank {
	@ManyToOne
	ArrayList<Account> accounts = new ArrayList<>();
	
	@Id
	@Column(name = "name")
	String name;

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
		if (type.toLowerCase().equals("credit"))
			person.setAccount(new CreditAccount());
		else if (type.toLowerCase().equals("bank"))
			person.setAccount(new BankAccount());
		if (person.getAccount().equals(null))
			return false;
		accounts.add(person.getAccount());
		return true;
	}

}
