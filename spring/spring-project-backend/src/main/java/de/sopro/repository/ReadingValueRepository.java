package de.sopro.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.sopro.data.Reading;
import de.sopro.data.ReadingValue;

@Repository
public interface ReadingValueRepository extends CrudRepository<ReadingValue, Long> {
	
	public Iterable<ReadingValue> findAllByReading(Reading reading);
	
//	
//	@Query(value = "Select rv FROM reading WHERE (rv.readingID = :readingID) ORDER BY lastChange DESC LIMIT 1)", nativeQuery = true)
//	Optional<ReadingValue> findCurrentValueToReadingId(@Param("readingID")Long readingID);
//	
	public List<ReadingValue> findByReadingOrderByLastChangeDesc(Reading r);

}
