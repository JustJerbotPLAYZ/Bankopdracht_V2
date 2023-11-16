package nl.jeroen.domain;

import java.util.ArrayList;

public class Bank {
	ArrayList<Account> accounts = new ArrayList<>();
	String name;
	
	public Bank(String name) {
		this.name = name;
	}
	
	public boolean transfer (int fromAccountNr, int toAccountNr, double amount) {
		return false;
	}
	
	private Account getAccountByNr(int accountNr) {
		return null;
	}
	
	public boolean registerAccount(Person person, String type) {
		return false;
	}
}
