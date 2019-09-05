package de.sopro.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Person {
	
	public Person(String username, String password, Role role) {
		this.role = role;
		this.username = username;
		this.password = password;
	}
	

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private String personID;
	
	@NotNull
	private String username;
	
	@NotNull
	@Size(min = 8, max = 50)
	private String password;
	
    @NotNull
	private Role role;

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
