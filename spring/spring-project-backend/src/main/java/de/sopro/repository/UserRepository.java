package de.sopro.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.sopro.data.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findByEMailAddress(String eMailAdress);

}
