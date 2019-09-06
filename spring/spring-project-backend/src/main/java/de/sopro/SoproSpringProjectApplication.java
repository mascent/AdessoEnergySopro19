package de.sopro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.sopro.data.Person;
import de.sopro.data.Role;
import de.sopro.data.User;
import de.sopro.repository.PersonRepository;
import de.sopro.repository.UserRepository;

@SpringBootApplication
public class SoproSpringProjectApplication {

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SoproSpringProjectApplication.class, args);
	}

	
	private void run() {
		
		personRepository.deleteAll();
		userRepository.deleteAll();
		personRepository.save(new Person("admin5", "password3", Role.Admin));
		
		userRepository.save(new User("usi", "ser", "a@b.c", "15", "usiser", "password15", Role.User));
		
	}

}
