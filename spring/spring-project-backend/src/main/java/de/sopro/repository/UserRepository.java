package de.sopro.repository;

import org.springframework.data.repository.CrudRepository;

import de.sopro.data.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

	User findByEMailAddress(String eMailAdress);

}
