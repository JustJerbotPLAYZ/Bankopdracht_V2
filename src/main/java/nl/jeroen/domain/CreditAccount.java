package nl.jeroen.domain;

import java.util.Random;

public class CreditAccount extends Account {
	private double creditLimit = 1000;
	private double creditAmount;
	private int securityCode;
	Random r = new Random();
	
	public CreditAccount() {
		
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
