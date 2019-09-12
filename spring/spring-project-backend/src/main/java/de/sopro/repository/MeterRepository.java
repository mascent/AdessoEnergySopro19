package de.sopro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.sopro.data.Meter;

@Repository
public interface MeterRepository extends CrudRepository<Meter, Long> {

}
