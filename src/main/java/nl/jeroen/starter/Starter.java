package nl.jeroen.starter;

import nl.jeroen.domain.Bank;
import nl.jeroen.domain.Person;

public class Starter {
	public static void main(String[] args) {
		Bank ABN = new Bank("ABN Amro");
		Person jeroen = new Person("Johannes Daniël Suurmond","",18);
		
		ABN.registerAccount(jeroen, "bank");
		System.out.println(jeroen.getAccount());
		System.out.println(jeroen.getAccount());
	}
}
