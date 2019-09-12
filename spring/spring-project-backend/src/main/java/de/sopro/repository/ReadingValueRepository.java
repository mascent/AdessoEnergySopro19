package de.sopro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.sopro.data.Reading;
import de.sopro.data.ReadingValue;

@Repository
public interface ReadingValueRepository extends CrudRepository<ReadingValue, Long> {
	
	public Iterable<ReadingValue> findAllByReading(Reading reading);
	
	
	@Query(value = "Select rv FROM reading WHERE (rv.readingID = :readingID) AND  ORDER BY readingDate DESC LIMIT 1)", nativeQuery = true)
	Optional<ReadingValue> findCurrentValueToReadingId(@Param("readingID")Long readingID);

}
