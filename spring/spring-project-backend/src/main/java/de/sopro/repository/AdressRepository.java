package de.sopro.repository;

import org.springframework.data.repository.CrudRepository;

import de.sopro.data.Address;

public interface AdressRepository extends CrudRepository<Address,String> {

}
