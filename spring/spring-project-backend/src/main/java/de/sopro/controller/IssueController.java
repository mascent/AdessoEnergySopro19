package de.sopro.controller;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class IssueController {
	
	@PostMapping("/api/issues")
	public String createIssue(@RequestParam String name, @RequestParam String email, @RequestParam String subject, @RequestParam String reason) {
		return null;
	}
	
	@PutMapping("/api/issues/{iid}")
	@DeleteMapping("/api/issues/{iid}")
	public String closeIssue(@RequestParam Jwt token, @PathVariable Long iid) {
		return null;
	}
	
	@GetMapping("/api/issues/{iid}")
	public String getIssue(@RequestParam Jwt token, @PathVariable Long iid) {
		return null;
	}
	
	@GetMapping("/api/issues")
	public String getIssues(@RequestParam Jwt token) {
		return null;
	}
}
