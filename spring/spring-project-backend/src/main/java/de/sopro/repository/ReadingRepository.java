package de.sopro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.sopro.data.Meter;
import de.sopro.data.Reading;

@Repository
public interface ReadingRepository extends CrudRepository<Reading, Long> {

	Iterable<Reading> findAllByMeter(Meter meter);

}
