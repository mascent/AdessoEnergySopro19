package de.sopro.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.sopro.data.Person;

public interface PersonRepository extends CrudRepository<Person,Long> {

	Person findByUsername(String username);
	
}
