package de.sopro.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.sopro.data.Person;
import de.sopro.data.Role;
import de.sopro.repository.PersonRepository;

@Service
public class AdessoUserDetailsService implements UserDetailsService {

	@Autowired
	PersonRepository personRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person = personRepository.findByUsername(username).orElse(null);

		if (person == null) {
			System.out.println("nonexistens " + username);
			throw new UsernameNotFoundException("a person by that username does not exists");
		}
		return User.withUsername(person.getUsername()).password(person.getPassword()).roles(person.getRole().toString(), Role.Shared.toString())
				.build();
	}

}
