package de.sopro.controller;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class MeterController {
	
	@PostMapping("/api/meters/{mid}/readings")
	public String addReading(@RequestParam Jwt token, @PathVariable Long mid, int value) {
		return null;
	}
	
	@PostMapping("/api/meters/{mid}/readings")
	public String addReadingViaPicture(@RequestParam Jwt token, @PathVariable Long mid) {
		return null;
	}
	
	@GetMapping("/api/meters/{mid}/readings")
	public String lookUpReadings(@RequestParam Jwt token, @PathVariable Long mid) {
		return null;
	}
	
	@PostMapping("api/meters")
	public String createMeter(@RequestParam Jwt token, @RequestParam String meterNumber, @RequestParam Reading initialReading) {
		return null;
	}
	
	@PutMapping("/api/meters/{mid}") //Bei Bedarf ein Attribut hinzufügen
	public String updateMeter(@RequestParam Jwt token, @PathVariable Long mid) {
		return null;
	}
	
	@DeleteMapping("/api/meters/{mid}")
	public String deleteMeter(@RequestParam Jwt token, @PathVariable Long mid) {
		return null;
	}
	
	@GetMapping("/api/meters")
	public String getMeters(@RequestParam Jwt token) {
		return null;
	}
	
	@GetMapping("api/meters/{mid}") 
	public String getMeter(@RequestParam Jwt token, @PathVariable Long mid) {
		return null;
	}

}
