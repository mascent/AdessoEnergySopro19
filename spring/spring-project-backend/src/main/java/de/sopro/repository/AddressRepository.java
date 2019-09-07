package de.sopro.repository;

import org.springframework.data.repository.CrudRepository;

import de.sopro.data.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
