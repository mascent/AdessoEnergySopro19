package de.sopro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.sopro.data.Reading;
import de.sopro.data.ReadingValue;

@Repository
public interface ReadingValueRepository extends CrudRepository<ReadingValue, Long> {
	
	public Iterable<ReadingValue> findAllByReading(Reading reading);

}
