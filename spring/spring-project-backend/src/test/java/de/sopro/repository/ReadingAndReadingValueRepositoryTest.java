package de.sopro.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.sopro.data.Reading;
import de.sopro.data.ReadingValue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ReadingAndReadingValueRepositoryTest {

	@Autowired
	ReadingRepository readingRepository;

	@Autowired
	ReadingValueRepository readingValueRepository;

	private Reading reading;

	@BeforeEach
	public void addReadingAndReadingValues() {
//		reading = new Reading((long) 1);
//		reading.getReadingValues().add(new ReadingValue((long) 1, null, "1"));
//		reading.getReadingValues().add(new ReadingValue((long) 2, null, "2"));
//		reading = readingRepository.save(reading);
	}

	// @Test
	public void testBooksArePersistedWithAuthor() throws Exception {

		assertTrue(readingRepository.existsById(reading.getReadingId()),
				"Reading #" + reading.getReadingId() + " exists in database");
//		for (ReadingValue rv : reading.getReadingValues()) {
//			assertTrue(readingValueRepository.existsById(rv.getReadingValueId()),
//					"Reading Value #" + rv.getReadingValueId() + " exists in database");
//		}
	}

}
