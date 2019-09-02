package de.sopro.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UserController {
	
	@PostMapping("api/users")
	public String createUser(Jwt token, User user) {
	return null;
	}
	
	@DeleteMapping("api/users/{uid}")
	public String deleteUser(Jwt token, @PathVariable Long uid) {
		return null;
	}
	
	@PutMapping("api/users/{uid}")
	public String updateUserName(@RequestParam Jwt token, @RequestParam String name, @PathVariable Long uid) {
		return null;
	}
	
	@PutMapping("api/users/{uid}")
	public String updateUserSurname(@RequestParam Jwt token, @RequestParam String name, @PathVariable Long uid) {
		return null;
	}
	
	@PutMapping("api/users/{uid}")
	public String updateUserEmail(@RequestParam Jwt token, @RequestParam String email, @PathVariable Long uid) {
		return null;
	}
	
	@PutMapping("api/users/me")
	public String updateOwnName(@RequestParam Jwt token, @RequestParam String name) {
		return null;
	}
	
	@PutMapping("api/users/{uid}")
	public String updateUserAddress(@RequestParam Jwt token, @RequestParam Address address, @PathVariable Long uid) {
		return null;
	}
	
	@PutMapping("api/users/{uid}")
	public String updateUserNumber(@RequestParam Jwt token, @RequestParam String number, @PathVariable Long uid) {
		return null;
	}

	@PutMapping("api/users/{uid}")
	public String addMetersToUser(@RequestParam Jwt token, @RequestParam List<Meter> meterIDs, @PathVariable Long uid) {
		return null;
	}
	
	@DeleteMapping("api/users/{uid}")
	public String removeMetersFromUser(@RequestParam Jwt token, @RequestParam List<Meter> meterIDs, @PathVariable Long uid) {
		return null;
	}
}
