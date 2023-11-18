package nl.jeroen.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "bankaccount")
public class BankAccount extends Account {
	public BankAccount() {
		
	}
	
	@Override
	public String toString() {
		return "bank";
	}
}
