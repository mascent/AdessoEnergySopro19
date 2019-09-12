package de.sopro.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.sopro.data.Meter;
import de.sopro.data.Reading;

@Repository
public interface ReadingRepository extends CrudRepository<Reading, Long> {

	Iterable<Reading> findAllByMeter(Meter meter);

	Optional<Reading> findByReadingIdAndMeter(Long readingId, Meter meter);

//	@Query(value = "Select * FROM reading WHERE (meterID = :meterID) AND (createdAt BETWEEN :from AND :to) ORDER BY created_at DESC LIMIT 1)", nativeQuery = true)
//	Optional<Reading> findCurrentReadingByMeterId(@Param("meterID") Long meterID,@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
//	
//	@Query(value = "Select * FROM reading WHERE (meterID = :meterID) AND (createdAt BETWEEN :from AND :to)", nativeQuery = true)
//	Iterable<Reading> findReadingByMeterIdFromDateToDate(@Param("meterID") Long meterID,@Param("from") Date from, @Param("to") Date to);

	List<Reading> findByMeterAndCreatedAtBetweenOrderByCreatedAtDesc(Meter meter, LocalDateTime fromDate,
			LocalDateTime toDate);

}
