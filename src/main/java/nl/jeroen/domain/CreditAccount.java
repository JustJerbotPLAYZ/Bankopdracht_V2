package nl.jeroen.domain;

import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "creditaccount")
public class CreditAccount extends Account {
	
	@Column(name = "creditlimit")
	private double creditLimit = 1000;
	
	@Column(name = "creditamount")
	private double creditAmount;
	
	@Column(name = "securitycode")
	private int securityCode;
	
	@Transient
	private Random r = new Random();
	
	public CreditAccount() {
		
	}
	
	public CreditAccount(Bank bank, Person person) {
		super(bank, person);
	}
	
	public void payCredit() {
		
	}
	
	public boolean withdraw(double amount) {
		return false;
	}
	
	public double getSpendingLimit() {
		return creditLimit;
	}
}
