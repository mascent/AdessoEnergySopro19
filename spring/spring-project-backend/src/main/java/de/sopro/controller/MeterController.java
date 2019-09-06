package de.sopro.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The meter controller contains operations to manage all requests belonging to
 * meters.
 * 
 * @author Mattis
 *
 */
@RestController
public class MeterController {

	

	/**
	 * This method allows an admin to get a list of all meters existing in the
	 * system.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @return A list of meters.
	 */
	@GetMapping("/api/meters")
	public String getMeters() {
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
	public String getMeter(@PathVariable Long mid) {
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
	@PostMapping("/api/meters")
	public String createMeter(@RequestParam String meterNumber,
			@RequestParam Long initialReading) {
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
	public String deleteMeter(@PathVariable Long mid) {
		return null;
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
	public String lookUpReadings( @PathVariable Long mid) {
		return null;
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
	public String addReading(@PathVariable Long mid, int value) {
		return null;
	}



}
