package nl.jeroen.domain;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import nl.jeroen.domain.persistence.factories.DAOFactory;

@Entity
@Table(name = "person")
public class Person {
	
	@Id
	@Column(name = "bsn")
	private String bsn;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private Integer age;

	@OneToOne
	@PrimaryKeyJoinColumn(name = "person")
	private Account account;

	public Person(String name, String bsn, Integer age) {
		this.bsn = bsn;
		this.name = name;
		this.age = age;
	}
	
	public void save() {
		DAOFactory.getFactory().getPersonDAO().save(this);
	}
	
	public void delete() {
		DAOFactory.getFactory().getPersonDAO().delete(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bsn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(account, other.account) && Objects.equals(age, other.age)
				&& Objects.equals(bsn, other.bsn) && Objects.equals(name, other.name);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
