package de.sopro.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.sopro.data.Meter;
import de.sopro.data.MeterType;
import de.sopro.data.Person;
import de.sopro.data.Reading;
import de.sopro.data.Role;
import de.sopro.data.User;
import de.sopro.data.UserMeterAssociation;
import de.sopro.dto.MeterDTO;
import de.sopro.repository.MeterRepository;
import de.sopro.repository.PersonRepository;
import de.sopro.repository.ReadingRepository;
import de.sopro.repository.UserMeterAssociationRepository;
import de.sopro.repository.UserRepository;

/**
 * The meter controller contains operations to manage all requests belonging to
 * meters.
 *
 */
@RestController
public class MeterController {

	@Autowired
	MeterRepository meterRepository;

	@Autowired
	ReadingRepository readingRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMeterAssociationRepository userMeterAssociationRepository;

	/**
	 * This method allows an admin to get a list of all meters existing in the
	 * system.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @return A list of meters.
	 */
	@GetMapping("/api/meters")
	public Iterable<MeterDTO> getMeters(HttpServletRequest request) {
		if (personRepository.findByUsername(request.getUserPrincipal().getName()).orElse(null).getRole()
				.equals(Role.Admin)) {
			return StreamSupport.stream(meterRepository.findAll().spliterator(), false).map(m -> new MeterDTO(m))
					.collect(Collectors.toList());
		}

		Iterable<UserMeterAssociation> umas = userMeterAssociationRepository
				.findAllByUser(userRepository.findByUsername(request.getUserPrincipal().getName()));

		return StreamSupport.stream(umas.spliterator(), false).map(uma -> new MeterDTO(uma.getMeter()))
				.filter(m -> m != null).distinct().collect(Collectors.toList());

	}

	/**
	 * This method allows an admin to create a new meter which is not belonging to
	 * anyone.
	 * 
	 * @param token          The JWT of the admin to authenticate himself.
	 * @param meterNumber    The number of the meter which is given from adesso
	 *                       energy.
	 * @param initialReading The initial reading of the counter. This is not 0 every
	 *                       time because for example the meter could change the
	 *                       provider.
	 * @return The ID of the created meter.
	 */
	@PostMapping("/api/meters")
	public MeterDTO createMeter(@RequestParam String meterNumber, @RequestParam Long initialReading,
			MeterType meterType) {

		Meter m = new Meter(meterNumber, initialReading, meterType);
		meterRepository.save(m);
		return new MeterDTO(m);
	}

	/**
	 * This method allows an user to get one of his meters by its ID or an admin to
	 * get any meter by its ad.
	 * 
	 * @param mid The ID of the meter that should be returned.
	 * @return The object of the meter belonging to the given ID or an error code if
	 *         no meter with the given ID exists.
	 */
	@GetMapping("/api/meters/{mid}")
	public String getMeter(HttpServletRequest request, @PathVariable Long mid) {

		Meter m = meterRepository.findById(mid).orElse(null);
		if (m == null) {
			return null;
		}

		if (personRepository.findByUsername(request.getUserPrincipal().getName()).orElse(null).getRole()
				.equals(Role.Admin)) {
			
		}

		return null;
	}

	// Hier unklar welches Attribut, definitiv adden oder Methode löschen.
	/**
	 * This method allows an user to update a meter to a current reading.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @param mid   The ID of the meter which should be updated.
	 * @return A boolean that shows if the update was successful.
	 */
	@PutMapping("/api/meters/{mid}")
	public String updateMeter(@PathVariable Long mid) {
		return null;
	}

	/**
	 * This method allows an admin to delete a meter out of the system.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @param mid   The ID of the meter which should be deleted.
	 * @return A boolean that shows if the update was successful.
	 */
	@DeleteMapping("/api/meters/{mid}")
	public Boolean deleteMeter(@PathVariable Long mid) {
		Meter m = meterRepository.findById(mid).orElse(null);

		if (m == null) {
			return false;
		}

		m.delet();
		meterRepository.save(m);
		return true;
	}

	/**
	 * This method allows an user to look up all readings belonging to one of his
	 * meters or an admin to look up all readings belonging to any meter.
	 * 
	 * @param token The JWT of the user/admin to authenticate himself.
	 * @param mid   The ID of the meter whose readings should be returned.
	 * @return A list of readings and dates they were uploaded.
	 */

	@GetMapping("/api/meters/{mid}/readings")
	public Iterable<Reading> lookUpReadings(HttpServletRequest request, @PathVariable Long mid) {

		Person p = personRepository.findByUsername(request.getUserPrincipal().getName()).orElse(null);
		Meter m = meterRepository.findById(mid).orElse(null);

		if (m == null || p == null) {
			return null;
		}

		if (p.getRole().equals(Role.User)) {
			User u = userRepository.findById(p.getPersonId()).orElse(null);
			assert u != null;
			Iterable<UserMeterAssociation> umas = userMeterAssociationRepository.findAllByUserAndMeter(u, m);
			List<Reading> readings = StreamSupport.stream(readingRepository.findAllByMeter(m).spliterator(), false)
					.map(r -> r).collect(Collectors.toList());
			for (UserMeterAssociation uma : umas) {
				for (Reading reading : readings) {
					if (uma.getEndOfAssociation() != null) {
						if (reading.getCreatedAt().isAfter(uma.getEndOfAssociation())) {
							readings.remove(reading);
						}
					}
					if (reading.getCreatedAt().isBefore(uma.getBeginOfAssociation())) {
						readings.remove(reading);
					}
				}
			}
			return readings;
		}
		return readingRepository.findAllByMeter(m);

	}

	/**
	 * This method allows an user to add a new reading to one of his meters or an
	 * admin to add a new reading to any meter. The reading is added as a textual
	 * input here.
	 * 
	 * @param token The JWT of the user/admin to authenticate himself.
	 * @param mid   The ID of the meter the new reading belongs to.
	 * @param value The value of the reading that should be added.
	 * @return A boolean that shows if the adding was successful.
	 */
	@PostMapping("/api/meters/{mid}/readings")
	public String addReading(HttpServletRequest request, @PathVariable Long mid, Long value) {
		Meter meter = meterRepository.findById(mid).orElse(null);
		User user = userRepository.findByUsername(request.getUserPrincipal().getName());

		if (meter == null || user == null) {
			return null;
		}

		Iterable<UserMeterAssociation> umas = userMeterAssociationRepository.findAllByUserAndMeter(user, meter);

		for (UserMeterAssociation uma : umas) {
			if (uma.getEndOfAssociation() == null) {
				readingRepository.save(new Reading(meter, value));
			}
		}

		return null;

	}

}
