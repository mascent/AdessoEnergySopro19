package de.sopro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import de.sopro.data.Person;
import de.sopro.data.Role;
import de.sopro.repository.PersonRepository;
import de.sopro.repository.UserRepository;

@SpringBootApplication
public class SoproSpringProjectApplication {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	PersonRepository personRepository;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SoproSpringProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(PersonRepository repo) {

		return args -> {
			if (repo.findByUsername("admin5") != null) {
				repo.deleteByUsername("admin5");
			}
			repo.save(new Person("admin5", passwordEncoder.encode("password3"), Role.Admin));
		};

//		userRepository.save(new User("usi", "ser", "a@b.c", "15", "usiser", "password15", Role.User));
//		
//		for(Person person : personRepository.findAll()) {
//			System.out.println(person.getPersonID() + "\t" + person.getUsername() + "\t" + person.getPassword());
//		}
	}
}
