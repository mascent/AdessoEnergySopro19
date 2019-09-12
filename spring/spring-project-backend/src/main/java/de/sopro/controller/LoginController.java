package de.sopro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.sopro.dto.PersonDTO;
import de.sopro.repository.PersonRepository;

@RestController
public class LoginController {

	@Autowired
	PersonRepository personRepository;

	@CrossOrigin
	@GetMapping("/api/login")
	public PersonDTO login(HttpServletRequest request) {
		// Since this is a valid request by an authenticated user, it should exists
		return new PersonDTO(personRepository.findByUsername(request.getUserPrincipal().getName()).orElse(null));
	}
}
