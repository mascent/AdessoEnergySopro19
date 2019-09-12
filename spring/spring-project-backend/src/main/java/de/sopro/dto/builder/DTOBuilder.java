package de.sopro.dto.builder;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.sopro.data.Meter;
import de.sopro.data.Reading;
import de.sopro.data.ReadingValue;
import de.sopro.data.User;
import de.sopro.data.UserMeterAssociation;
import de.sopro.dto.MeterDTO;
import de.sopro.dto.ReadingDTO;
import de.sopro.repository.MeterRepository;
import de.sopro.repository.ReadingRepository;
import de.sopro.repository.ReadingValueRepository;
import de.sopro.repository.UserMeterAssociationRepository;
import de.sopro.repository.UserRepository;
import de.sopro.util.exception.ResourceNotFoundException;

@Component
public class DTOBuilder {

	@Autowired
	MeterRepository mRepo;

	@Autowired
	ReadingRepository rRepo;

	@Autowired
	ReadingValueRepository rvRepo;

	@Autowired
	UserRepository uRepo;

	@Autowired
	UserMeterAssociationRepository umaRepo;

	public MeterDTO meterDTO(Meter m) throws ResourceNotFoundException {

		List<UserMeterAssociation> uma = umaRepo.findCurrentUserOfMeter(m.getMeterId());
		User user = uma.size() > 0 ? uma.get(0).getUser() : null;

		return meterDTO(m, user);

	}

	public MeterDTO meterDTO(Meter m, User user) throws ResourceNotFoundException {

		String name = m.getMeternumber();
		Reading lastReading = null;
		if (user != null) {
			Iterable<UserMeterAssociation> umas = umaRepo.findAllByUserAndMeter(user, m);
			for (UserMeterAssociation uma : umas) {
				name = uma.getMeterName();
				LocalDateTime to = uma.getEndOfAssociation();
				List<Reading> readings = rRepo.findByMeterAndCreatedAtBetweenOrderByCreatedAtDesc(m, uma.getBeginOfAssociation(),
						to == null ? LocalDateTime.now() : to);
				if(readings.size() > 0) {
				lastReading = readings.get(0);
				return new MeterDTO(m, user.getPersonId(), name, readingDTO(lastReading));
				}
				return new MeterDTO(m, user.getPersonId(), name, new ReadingDTO());
			}
			throw new ResourceNotFoundException();
		}

		// TODO
		lastReading = rRepo.findByMeterAndCreatedAtBetweenOrderByCreatedAtDesc(m, m.getCreatedAt(), LocalDateTime.now())
				.get(0);
		return new MeterDTO(m, user.getPersonId(), name, readingDTO(lastReading));

	}

	public ReadingDTO readingDTO(Reading r) {
		List<ReadingValue> rvs = rvRepo.findByReadingOrderByLastChangeDesc(r);
		ReadingValue rv = null;
		if (rvs.size() > 0) {
			rv = rvs.get(0);
		}
		return new ReadingDTO(r, rv);// rv);
	}

}
