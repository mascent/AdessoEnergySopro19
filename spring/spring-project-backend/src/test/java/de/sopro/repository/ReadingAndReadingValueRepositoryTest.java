package de.sopro.repository;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
			reading = new Reading();
			reading.getReadingValues().add(new ReadingValue(1, null, 1));
			reading.getReadingValues().add(new ReadingValue(2, null, 2));
			reading = readingRepository.save(reading);
		}
		
		
		@Test
		public void testBooksArePersistedWithAuthor() throws Exception {

			assertTrue("Reading #" + reading.getReadingID() + " exists in database",
					readingRepository.existsById(reading.getReadingID()));
			for (ReadingValue rv : reading.getReadingValues()) {
				assertTrue("Reading Value #" + rv.getReadingValueID() + " exists in database",
						readingValueRepository.existsById(rv.getReadingValueID()));
			}
		}
		
		
}
