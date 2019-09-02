package de.sopro.controller;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class PictureController {

	@PostMapping("api/pictures")
	public String analyze(@RequestParam Jwt token, @RequestParam Picture pic) {
		return null;
	}
}
