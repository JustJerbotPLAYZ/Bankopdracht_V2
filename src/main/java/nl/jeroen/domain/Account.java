package nl.jeroen.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public abstract class Account {
	
	@Id
	@Column(name = "accountNr", unique = true)
	protected Integer accountNr;
	
	@Column(name = "balance")
	protected double balance = 0;
	
	@ManyToOne
	@Column(name="FK_Bankname")
	private Bank bank;

	public static int nextAccountNr = 1000;

	public Account() {
		accountNr = nextAccountNr;
		nextAccountNr++;
	}

	public void deposit(double amount) {
		balance = +amount;
	}

	public boolean withdraw(double amount) {
		if (balance < amount)
			return false;
		balance = -amount;
		return true;
	}

	public Integer getAccountNr() {
		return accountNr;
	}

	@Override
	public String toString() {
		return accountNr.toString();
	}
}
