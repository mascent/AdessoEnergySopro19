package de.sopro.controller;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.sopro.data.Meter;
import de.sopro.data.MeterType;
import de.sopro.data.Person;
import de.sopro.data.Reading;
import de.sopro.data.ReadingValue;
import de.sopro.data.Role;
import de.sopro.data.User;
import de.sopro.data.UserMeterAssociation;
import de.sopro.repository.MeterRepository;
import de.sopro.repository.PersonRepository;
import de.sopro.repository.ReadingRepository;
import de.sopro.repository.UserRepository;

/**
 * The meter controller contains operations to manage all requests belonging to
 * meters.
 * 
 * @author Mattis
 *
 */
@Controller
public class MeterController {

	@Autowired
	MeterRepository meterRepository;

	@Autowired
	ReadingRepository readingRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	UserRepository userRepository;

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
	public String addReading(@RequestParam Jwt token, @PathVariable String mid, int value) {
		Meter meter = meterRepository.findById(mid); // Zähler finden //wie hier optional fixen?
		List<Reading> readingsList = meter.getReadings(); // alle Stände des Zählers
		int pos = readingsList.size() - 1;
		Reading reading = readingsList.get(pos); // aktuellster Stand steht an letzter Stelle
		List<ReadingValue> valuesList = reading.getReadingValues(); // alle Values des Standes bekommen (mehrere Stände
																	// durch Update)
		int pos1 = valuesList.size() - 1;
		ReadingValue actualValue = valuesList.get(pos1); // aktuellsten Stand kriegen
		int oldValue = actualValue.getValue(); // von dem den Wert auslesen, da Rest nicht interessiert
		if (value >= oldValue) {
			Reading newReading = new Reading();
			String changerId = token.getId(); // hier gucken, wie das geht..
			Date date = new Date();
			ReadingValue newReadingValue = new ReadingValue(value, date, changerId);
			List<ReadingValue> newValuesList = new ArrayList<>();
			newValuesList.add(newReadingValue);
			newReading.setReadingValues(newValuesList);
		}
	}

//	/** //Vincent sagt brauchen wir nicht
//	 * This method allows an user to add a new reading to one of his meters or an
//	 * admin to add a new reading to any meter. The reading is added as a picture
//	 * here which is evaluated by another method.
//	 * 
//	 * @param token The JWT of the user/admin to authenticate himself.
//	 * @param mid   The ID of the meter the new reading belongs to.
//	 * @return A boolean that shows if the adding was successful.
//	 */
//	@PostMapping("/api/meters/{mid}/readings")
//	public String addReadingViaPicture(@RequestParam Jwt token, @PathVariable Long mid, @RequestParam Image pic) {
//		int value = PictureController.analyze(token, pic);
//		Meter meter = meterRepository.findById(mid); // Zähler finden //wie hier optional fixen?
//		List<Reading> readingsList = meter.getReadings(); // alle Stände des Zählers
//		int pos = readingsList.size() - 1;
//		Reading reading = readingsList.get(pos); // aktuellster Stand steht an letzter Stelle
//		List<ReadingValue> valuesList = reading.getReadingValues(); // alle Values des Standes bekommen (mehrere Stände
//																	// durch Update)
//		int pos1 = valuesList.size() - 1;
//		ReadingValue actualValue = valuesList.get(pos1); // aktuellsten Stand kriegen
//		int oldValue = actualValue.getValue(); // von dem den Wert auslesen, da Rest nicht interessiert
//		if (value >= oldValue) {
//			Reading newReading = new Reading();
//			String changerId = token.getId(); // hier gucken, wie das geht..
//			Date date = new Date();
//			ReadingValue newReadingValue = new ReadingValue(value, date, changerId);
//			List<ReadingValue> newValuesList = new ArrayList<>();
//			newValuesList.add(newReadingValue);
//			newReading.setReadingValues(newValuesList);
//		}
//	}

	/**
	 * This method allows an user to look up all readings belonging to one of his
	 * meters or an admin to look up all readings belonging to any meter.
	 * 
	 * @param token The JWT of the user/admin to authenticate himself.
	 * @param mid   The ID of the meter whose readings should be returned.
	 * @return A list of readings and dates they were uploaded.
	 */
	@GetMapping("/api/meters/{mid}/readings")
	public List<Reading> lookUpReadings(@RequestParam Jwt token, @PathVariable String mid) {
		String userId = token.getId(); // hier gucken, wie das geht..
		Person person = personRepository.findById(userId);
		if (person.getRole().equals(Role.Admin)) {
			Meter newMeter = meterRepository.findById(mid);
			List<Reading> readingList = newMeter.getReadings();
			return readingList;
		} else if (person.getRole().equals(Role.User)) { // hier noch Check ob User berechtigt ist, also ob sein Zähler,
															// probably über UserMeterAssociation
			Meter newMeter = meterRepository.findById(mid);
			List<Reading> readingList = newMeter.getReadings();
			return readingList;
		}
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
	@PostMapping("api/meters")
	public String createMeter(@RequestParam Jwt token, @RequestParam String meterNumber,
			@RequestParam int initialReading, MeterType meterType) {
		if (!meterNumber.isEmpty() && initialReading >= 0 && (meterType.equals("Gas") || meterType.equals("Water") || meterType.equals("Electricity"))) {
			String creatorId = token.getId(); //hier gucken, wie das geht..
			Person person = personRepository.findById(creatorId);
			if(person.getRole().equals(Role.Admin)) {
				Meter meter = new Meter(meterNumber, initialReading, meterType)
				String meterId = meter.getMeterId();
				meterRepository.save(meter);
				return meterId;
			}
			return null; //wahrscheinlich lieber Fehler
		}
		return null; //wahrscheinlich lieber Fehler
	}

	/**
	 * This method allows an admin to create a new meter which is belonging to a
	 * user.
	 * 
	 * @param token          The JWT of the admin to authenticate himself.
	 * @param meterNumber    The number of the meter which is given from adesso
	 *                       energy.
	 * @param uid            The ID of the user this meter belongs to.
	 * @param initialReading The initial reading of the counter. This is not 0 every
	 *                       time because for example the meter could change the
	 *                       provider.
	 * @return The ID of the created meter.
	 */
	@PostMapping("api/meters")
	public String createMeter(@RequestParam Jwt token, @RequestParam String meterNumber, @RequestParam String uid,
			@RequestParam int initialReading, MeterType meterType) {
		if (!meterNumber.isEmpty() && initialReading >= 0 && (meterType.equals("Gas") && meterType.equals("Water") && meterType.equals("Electricity"))) {
			String creatorId = token.getId(); //hier gucken, wie das geht..
			Person person = personRepository.findById(creatorId);
			if(person.getRole().equals(Role.Admin)) {
				Meter meter = new Meter(meterNumber, initialReading, meterType)
				String meterId = meter.getMeterId();
				meterRepository.save(meter);
				User user = userRepository.findById(uid)
				UserMeterAssociation connection = new UserMeterAssociation(user, meter);
				return meterId;
			}
			return null; //wahrscheinlich lieber Fehler
		}
		return null; //wahrscheinlich lieber Fehler
	}

	}

	// Hier unklar welches Attribut, definitiv adden oder Methode löschen.
	/**
	 * This method allows an admin to update a meter.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @param mid   The ID of the meter which should be updated.
	 * @return A boolean that shows if the update was successful.
	 */
	@PutMapping("/api/meters/{mid}")
	public String updateMeter(@RequestParam Jwt token, @PathVariable String mid) {
		return null; // Was wollen wir updaten?
	}

	/**
	 * This method allows an admin to delete a meter out of the system.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @param mid   The ID of the meter which should be deleted.
	 * @return A boolean that shows if the update was successful.
	 */
	@DeleteMapping("/api/meters/{mid}")
	public String deleteMeter(@RequestParam Jwt token, @PathVariable String mid) {
		String deleterId = token.getId(); // hier gucken, wie das geht..
		Person person = personRepository.findById(deleterId);
		Meter meter = meterRepository.findById(mid);
		if (person.getRole().equals(Role.Admin)) {
			Date date = new Date();
			meter.setDeletedAt(date);
		}
	}

	/**
	 * This method allows an admin to get a list of all meters existing in the
	 * system.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @return A list of meters.
	 */
	@GetMapping("/api/meters")
	public List<Meter> getMeters(@RequestParam Jwt token) {
		String closerId = token.getId();
		Person person = personRepository.findById(closerId);
		if (person.getRole().equals(Role.Admin)) {
			List<Meter> metersList = (List<Meter>) meterRepository.findAll();
			return metersList;
		}
		return null; // lieber Fehler
	}

	/**
	 * This method allows an user to get one of his meters by its ID or an admin to
	 * get any meter by its ad.
	 * 
	 * @param token The JWT of the user/admin to authenticate himself.
	 * @param mid   The ID of the meter that should be returned.
	 * @return The object of the meter belonging to the given ID or an error code if
	 *         no meter with the given ID exists.
	 */
	@GetMapping("api/meters/{mid}")
	public Meter getMeter(@RequestParam Jwt token, @PathVariable String mid) {
		String closerId = token.getId();
		Person person = personRepository.findById(closerId);
		if (person.getRole().equals(Role.Admin)) {
			Meter meter = meterRepository.findById(mid);
			return meter;
		} else if (person.getRole().equals(Role.User)) { // nur wenn Zähler zu User gehört, über User Meter Asso
			Meter meter = meterRepository.findById(mid);
			return meter;
		}
	}
}
