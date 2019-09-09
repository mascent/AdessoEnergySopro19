package de.sopro.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.sopro.data.Meter;
import de.sopro.data.MeterType;
import de.sopro.data.Reading;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MeterAndReadingRepositoryTest {

	@Autowired
	MeterRepository meterRepository;

	@Autowired
	ReadingRepository readingRepository;

	private Meter meter;

//	@BeforeEach
	public void addReadingAndReadingValues() {
		meter = new Meter("GasTest", (long) 0, MeterType.Gas);
		meter.getReadings().add(new Reading());
		meter.getReadings().add(new Reading());
		meter = meterRepository.save(meter);
	}

	// @Test
	public void testBooksArePersistedWithAuthor() throws Exception {

		assertTrue(readingRepository.existsById(meter.getMeterId()),
				"Meter #" + meter.getMeterId() + " exists in database");
		for (Reading r : meter.getReadings()) {
			assertTrue(readingRepository.existsById(r.getReadingId()),
					"Reading Value #" + r.getReadingId() + " exists in database");
		}
	}

}
