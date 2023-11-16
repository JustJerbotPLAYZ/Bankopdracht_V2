package nl.jeroen.domain;

public abstract class Account {
	protected double balance = 0;
	protected Integer accountNr;
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
}
