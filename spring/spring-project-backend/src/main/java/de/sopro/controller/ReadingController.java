package de.sopro.controller;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The reading controller contains operations to manage all requests belonging
 * to readings which are belonging to a meter.
 * 
 * @author Mattis
 *
 */
public class ReadingController {

	/**
	 * This method allows an admin to update a reading. This means to change the
	 * reading value to another value because the old value was incorrect for some
	 * reason.
	 * 
	 * @param token  The JWT of the admin to authenticate himself.
	 * @param mid    The ID of the meter to which the reading that should be changed
	 *               belongs.
	 * @param value  The new value that should be inserted for this reading.
	 * @param rid    The ID of the reading that should be changed.
	 * @param reason The reason why the reading was changed. This secures that the
	 *               history of a reading is trackable.
	 * @return A boolean that shows if the change was successful.
	 */
	@PutMapping("api/meters/{mid}/readings/{rid}")
	public String updateReading(@RequestParam Jwt token, @PathVariable Long mid, @RequestParam int value,
			@PathVariable Long rid, @RequestParam String reason) {
		return null;
	}

	/**
	 * This method allows an admin to get the history of all changes made to a
	 * reading including the reasons why these changes were made.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @param mid   The ID of the meter to which the reading that should be returned
	 *              belongs.
	 * @param rid   The ID of the reading that should be returned.
	 * @return A list of reading values, the dates they were changed and the reason
	 *         for the changes.
	 */
	@GetMapping("api/meters/{mid}/readings/{rid}")
	public String getReadingHistory(@RequestParam Jwt token, @PathVariable Long mid, @PathVariable Long rid) {
		return null;
	}

	/**
	 * This method allows an admin to hide a reading out of the list of readings
	 * associated with a meter. The reading is still saved but not visible in
	 * general.
	 * 
	 * @param token  The JWT of the admin to authenticate himself.
	 * @param mid    The ID of the meter to which the reading that deleted be
	 *               changed belongs.
	 * @param rid    The ID of the reading that should be deleted.
	 * @param reason The reason why the reading was deleted. This reason is saved so
	 *               that other admins can see why the reading was deleted out of
	 *               the list of readings.
	 * @return A boolean that shows if the deletion was successful.
	 */
	@PutMapping("api/meters/{mid}/readings/{rid}")
	public String deleteReading(@RequestParam Jwt token, @PathVariable Long mid, @PathVariable Long rid,
			@RequestParam String reason) {
		return null;
	}

}
