package de.sopro.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@CrossOrigin
	@GetMapping("/api/login")
	public Boolean login() {
		return true;
	}
}
