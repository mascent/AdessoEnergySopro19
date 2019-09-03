package de.sopro.controller;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.sopro.data.Reading;

/**
 * The meter controller contains operations to manage all requests belonging to
 * meters.
 * 
 * @author Mattis
 *
 */
@Controller
public class MeterController {

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
	public String addReading(@RequestParam Jwt token, @PathVariable Long mid, int value) {
		return null;
	}

	/**
	 * This method allows an user to add a new reading to one of his meters or an
	 * admin to add a new reading to any meter. The reading is added as a picture
	 * here which is evaluated by another method.
	 * 
	 * @param token The JWT of the user/admin to authenticate himself.
	 * @param mid   The ID of the meter the new reading belongs to.
	 * @return A boolean that shows if the adding was successful.
	 */
	@PostMapping("/api/meters/{mid}/readings")
	public String addReadingViaPicture(@RequestParam Jwt token, @PathVariable Long mid) {
		return null;
	}

	/**
	 * This method allows an user or to look up all readings belonging to one of his
	 * meters or an admin to look up all readings belonging to any meter.
	 * 
	 * @param token The JWT of the user/admin to authenticate himself.
	 * @param mid   The ID of the meter whose readings should be returned.
	 * @return A list of readings and dates they were uploaded.
	 */
	@GetMapping("/api/meters/{mid}/readings")
	public String lookUpReadings(@RequestParam Jwt token, @PathVariable Long mid) {
		return null;
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
			@RequestParam Reading initialReading) {
		return null;
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
	public String createMeter(@RequestParam Jwt token, @RequestParam String meterNumber, @RequestParam Long uid,
			@RequestParam Reading initialReading) {
		return null;
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
	public String updateMeter(@RequestParam Jwt token, @PathVariable Long mid) {
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
	public String deleteMeter(@RequestParam Jwt token, @PathVariable Long mid) {
		return null;
	}

	/**
	 * This method allows an admin to get a list of all meters existing in the
	 * system.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @return A list of meters.
	 */
	@GetMapping("/api/meters")
	public String getMeters(@RequestParam Jwt token) {
		return null;
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
	public String getMeter(@RequestParam Jwt token, @PathVariable Long mid) {
		return null;
	}

}
