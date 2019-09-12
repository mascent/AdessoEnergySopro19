package de.sopro.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.sopro.data.Meter;
import de.sopro.data.User;
import de.sopro.data.UserMeterAssociation;
import de.sopro.dto.MeterDTO;
import de.sopro.dto.builder.DTOBuilder;
import de.sopro.repository.MeterRepository;
import de.sopro.repository.UserMeterAssociationRepository;
import de.sopro.repository.UserRepository;
import de.sopro.util.exception.ResourceNotFoundException;

@RestController
public class UserMeterAssociationController {

	@Autowired
	UserMeterAssociationRepository userMeterAssociationRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	MeterRepository meterRepository;

	@Autowired
	DTOBuilder dtoBuilder;

	@PutMapping("api/meters/{mid}/users")
	@CrossOrigin
	public Boolean addMeterToUser(@RequestParam Long userId, @PathVariable Long mid) {
		User u = userRepository.findById(userId).orElse(null);
		if (u == null) {
			return false;
		}
		// Check if meter exists

		Meter m = meterRepository.findById(mid).orElse(null);
		// Do nothing for non existent meters
		if (m == null) {
			return false;
		}

		// Don't add meters that are still in use by another user.
		Iterable<UserMeterAssociation> associations = userMeterAssociationRepository.findAllByMeter(m);
		for (UserMeterAssociation a : associations) {
			if (a.getEndOfAssociation() == null) {
				continue;
			}
		}

		// Add meter to user, if it isn't in use and exists.
		userMeterAssociationRepository.save(new UserMeterAssociation(u, m));
		return true;

	}

	/**
	 * This method allows an admin to add new meters to the account of an user. The
	 * meters are stored as a list in the database entry of the user its belonging
	 * to.
	 * 
	 * @param token    The JWT of the admin to authenticate himself.
	 * @param meterIDs The list of meters which should be added to the users
	 *                 account. The list needs to contain at least one meter.
	 * @param uid      The ID of the user who should become new meters associated
	 *                 with himself.
	 * @return A boolean that shows if the operation was successful.
	 */
	@PutMapping("/api/users/{uid}/meters")
	@CrossOrigin
	public boolean addMetersToUser(@RequestParam List<Long> meterIDs, @PathVariable Long uid) {

		User u = userRepository.findById(uid).orElse(null);
		if (u == null) {
			return false;
		}

		for (Long mID : meterIDs) {
			addMeterToUser(uid, mID);

		}
		return false;

	}

	@GetMapping("/api/users/{uid}/meters")
	@CrossOrigin
	public Iterable<MeterDTO> getUsersMeters(@PathVariable Long uid) throws ResourceNotFoundException {

		User u = userRepository.findById(uid).orElseThrow(() -> new ResourceNotFoundException());
		return StreamSupport.stream(userMeterAssociationRepository.findAllByUser(u).spliterator(), false).map(uma -> {
			try {
				return dtoBuilder.meterDTO(uma.getMeter(), u);
			} catch (ResourceNotFoundException e) {
				return null;
			}
		}).filter(x -> x != null).collect(Collectors.toList());

	}

	@GetMapping("/api/users/me/meters")
	@CrossOrigin
	public Iterable<MeterDTO> getUsersMeters(HttpServletRequest request) throws ResourceNotFoundException {
		User u = userRepository.findByUsername(request.getUserPrincipal().getName()).orElse(null);
		return getUsersMeters(u.getPersonId());

	}

	@DeleteMapping("/api/meters/{mid}/users")
	@CrossOrigin
	public Boolean removeMeterFromUser(@RequestParam Long uid, @PathVariable Long mid) {
		User u = userRepository.findById(uid).orElse(null);
		Meter m = meterRepository.findById(mid).orElse(null);

		if (u != null && m != null) {
			Iterable<UserMeterAssociation> umas = userMeterAssociationRepository.findAllByUserAndMeter(u, m);
			for (UserMeterAssociation uma : umas) {
				if (uma.getEndOfAssociation() == null) {
					uma.endAssociation();
					return true;
				}

			}
		}

		return false;
	}

	/**
	 * This method allows an admin to delete meters from an user account. So to
	 * remove meters from the list of meters associated with this users account
	 * 
	 * @param token    The JWT of the admin to authenticate himself.
	 * @param meterIDs The list of meters which should be deleted from the users
	 *                 account. The list needs to contain at least one meter.
	 * @param uid      The ID of the user who should have less meters associated
	 *                 with his account.
	 * @return A boolean that shows if the operation was successful.
	 */
	@DeleteMapping("/api/users/{uid}/meters")
	@CrossOrigin
	public Boolean removeMetersFromUser(@RequestParam List<Long> meterIDs, @PathVariable Long uid) {

		for (Long mID : meterIDs) {
			if (!removeMeterFromUser(uid, mID)) {
				return false;
			}
		}

		return true;
	}

}
