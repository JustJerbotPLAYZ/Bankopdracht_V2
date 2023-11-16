package nl.jeroen.domain;

import java.util.Objects;

public class Person {
	private String name;
	private Integer age;
	private String bsn;
	private Account account;
	
	public Person(String bsn, String name, Integer age) {
		this.bsn = bsn;
		this.name = name;
		this.age = age;
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
