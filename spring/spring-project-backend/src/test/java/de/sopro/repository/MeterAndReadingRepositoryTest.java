package de.sopro.repository;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.sopro.data.Meter;
import de.sopro.data.Reading;
import de.sopro.data.ReadingValue;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MeterAndReadingRepositoryTest {

			@Autowired
			MeterRepository meterRepository;
			
			@Autowired
			ReadingRepository readingRepository;
			
			private Meter meter;
			
			@BeforeEach
			public void addReadingAndReadingValues() {
				meter = new Meter("GasTest",null);
				meter.getReadings().add(new Reading());
				meter.getReadings().add(new Reading());
				meter = meterRepository.save(meter);
			}
			
			
			@Test
			public void testBooksArePersistedWithAuthor() throws Exception {

				// Check if books belonging to an author have also been persisted
				// when the author was added to the repository

				assertTrue("Meter #" + meter.getMeterID() + " exists in database",
						readingRepository.existsById(meter.getMeterID()));
				for (Reading r : meter.getReadings()) {
					assertTrue("Reading Value #" + r.getReadingID() + " exists in database",
							readingRepository.existsById(r.getReadingID()));
				}
			}
			
			
	}
	
	
}
