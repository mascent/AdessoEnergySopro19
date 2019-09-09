package de.sopro.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.sopro.data.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

	public Optional<Person> findByUsername(String username);

	public void deleteByUsername(String username);

}
