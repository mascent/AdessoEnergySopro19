package de.sopro.repository;

import org.springframework.data.repository.CrudRepository;

import de.sopro.data.Adress;

public interface AdressRepository extends CrudRepository<Adress,String> {

}
