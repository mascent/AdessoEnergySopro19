package de.sopro.controller;

import java.awt.Image;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The picture controller contains operations to evaluate pictures.
 * 
 *
 */
@RestController
public class PictureController {

	/**
	 * This method analyzes a picture. This means it trys to detect a meter in the
	 * picture. If that was successful it trys to extract the meter number and the
	 * reading of this meter out of the picture.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @param pic   The picture that should be analyzed.
	 * @return The meter number and the reading of this meter. If one or both are
	 *         not found return error code.
	 */
	@PostMapping("/api/pictures")
	public String analyze(@RequestParam Image pic) {
		return null;
	}
}
