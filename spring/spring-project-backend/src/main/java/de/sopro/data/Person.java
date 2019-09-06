package de.sopro.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long personID;

	@NotNull    
	@Column(nullable = false, unique = true)
	private String username;

	@NotNull
	//@Size(min = 8, max = 50)
	private String password;

	private Role role;

	public Person(String username, String password, Role role) {
		this.role = role;
		this.username = username;
		this.password = password;
	}

	public Person() {

	}

	public Long getPersonID() {
		return personID;
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
