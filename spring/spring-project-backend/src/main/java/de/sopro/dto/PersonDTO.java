package de.sopro.dto;

import de.sopro.data.Person;

public class PersonDTO {

	private Long id;

	private String username;

	private String role;

	public PersonDTO(Person p) {
		this.id = p.getPersonId();
		this.username = p.getUsername();
		this.role = p.getRole().toString();
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getRole() {
		return role;
	}

}
