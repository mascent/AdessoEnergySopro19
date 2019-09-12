package de.sopro.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.sopro.data.Meter;
import de.sopro.data.Reading;

@Repository
public interface ReadingRepository extends CrudRepository<Reading, Long> {

	Iterable<Reading> findAllByMeter(Meter meter);
	
	Optional<Reading> findByReadingIdAndMeter(Long readingId, Meter meter);
	
	@Query(value = "Select r FROM reading WHERE (r.meterID = :meterID) AND (r.createdAt BETWEEN :from AND :to) AND  ORDER BY createdAt DESC LIMIT 1)", nativeQuery = true)
	Optional<Reading> findCurrentReadingByMeterId(@Param("meterID") Long meterID,@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
	
	@Query(value = "Select r FROM reading WHERE (r.meterID = :meterID) AND (r.createdAt BETWEEN :from AND :to)", nativeQuery = true)
	Iterable<Reading> findReadingByMeterIdFromDateToDate(@Param("meterID") Long meterID,@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
	

	
}
