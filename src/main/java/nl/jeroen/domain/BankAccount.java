package nl.jeroen.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "bankaccount")
public class BankAccount extends Account {
	
	public BankAccount() {
		
	}
	
	public BankAccount(Bank bank, Person person) {
		super(bank, person);
	}
}
