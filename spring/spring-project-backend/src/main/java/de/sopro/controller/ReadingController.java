package de.sopro.controller;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ReadingController {
	
	@PutMapping("api/meters/{mid}/readings/{rid}")
	public String updateReading(@RequestParam Jwt token, @PathVariable  Long mid, @RequestParam int value, @PathVariable Long rid, @RequestParam String reason) {
		return null;
	}
	
	@GetMapping("api/meters/{mid}/readings/{rid}")
	public String getReadingHistory(@RequestParam Jwt token, @PathVariable  Long mid, @PathVariable  Long rid) {
		return null;
	}
	
	@PutMapping("api/meters/{mid}/readings/{rid}")
	public String deleteReading(@RequestParam Jwt token, @PathVariable  Long mid, @PathVariable Long rid, @RequestParam String reason) {
		return null;
	}

}
