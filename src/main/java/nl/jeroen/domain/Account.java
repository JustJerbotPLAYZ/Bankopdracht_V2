package nl.jeroen.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import nl.jeroen.domain.persistence.factories.DAOFactory;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "accounttype")
public class Account implements Comparable<Account>{
	
    @Id
    @Column(name = "accountNr", unique = true)
    protected Integer accountNr;

    @Column(name = "balance")
    protected double balance = 0;

    @ManyToOne
    @JoinColumn(name = "bankname")
    private Bank bank;
    
	@OneToOne(mappedBy = "account")
    private Person accountHolder;

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
	
	@Override
	public int compareTo(Account otherAccount) {
		return this.getAccountNr().compareTo(otherAccount.getAccountNr());
	}
	
	public void save() {
		DAOFactory.getFactory().getAccountDAO().save(this);
	}
	
	public void delete() {
		DAOFactory.getFactory().getAccountDAO().delete(this);
	}

	public Integer getAccountNr() {
		return accountNr;
	}

	@Override
	public String toString() {
		return accountNr.toString();
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Person getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(Person accountHolder) {
		this.accountHolder = accountHolder;
	}
}
