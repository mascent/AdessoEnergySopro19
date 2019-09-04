package de.sopro.repository;

import org.springframework.data.repository.CrudRepository;

import de.sopro.data.Adress;

public interface ReadingRepository extends CrudRepository<Adress,String> {

}
