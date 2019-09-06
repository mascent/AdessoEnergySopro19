package de.sopro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.sopro.data.Meter;
import de.sopro.data.Role;
import de.sopro.data.User;
import de.sopro.dto.UserDTO;
import de.sopro.repository.MeterRepository;
import de.sopro.repository.PersonRepository;
import de.sopro.repository.UserMeterAssociationRepository;
import de.sopro.repository.UserRepository;

/**
 * The user controller contains operations manage all requests belonging to user
 * accounts.
 * 
 *
 */
@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	UserMeterAssociationRepository userMeterAssociationRepository;

	@Autowired
	MeterRepository meterRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/api/users")
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}

	/**
	 * This method allows an admin to create a new user in the system. The user
	 * object is created in before, given as a parameter and stored in the database
	 * when this method is executed.
	 *
	 * @param token The JWT of the admin to authenticate himself.
	 * @param user  The object of the user to store in the database.
	 * @return A boolean that shows if the creation was successful.
	 */
	@PostMapping("/api/users")
	public UserDTO createUser(@RequestParam String name, @RequestParam String surname, @RequestParam String eMailAdress,
			@RequestParam String userNumber, @RequestParam String username, @RequestParam String password) {
		return new UserDTO(userRepository
				.save(new User(name, surname, eMailAdress, username, passwordEncoder.encode(password), Role.User)));

	}

	/**
	 * This method allows an admin to delete a user out of the system. The user
	 * object is deleted out of the database.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @param uid   The ID of the user that should be deleted.
	 * @return
	 * @return A boolean that shows if the deletion was successful.
	 */
	@DeleteMapping("api/users")
	public boolean deleteUser(@RequestParam Long uid) {

		if (userRepository.existsById(uid)) {
			userRepository.deleteById(uid);
			return true;
		}

		return false;
	}

	/**
	 * This method allows an admin to change the surname of an existing user in the
	 * database.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @param name  The new surname that should occure in the database entry of that
	 *              user.
	 * @param uid   The ID of the user whose surname should be changed.
	 * @return A boolean that shows if the change was successful.
	 */
	@PutMapping("api/users/{uid}/surname")
	public UserDTO updateUserSurname(@RequestParam String object, @RequestParam String surname,
			@PathVariable Long uid) {
		User u = userRepository.findById(uid).orElse(null);
		if (u != null) {
			u.setSurname(surname);
			userRepository.save(u);
			return new UserDTO(u);
		}
		return null;
	}

	/**
	 * This method allows an admin to change the email adress of an existing user in
	 * the database.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @param name  The new email adress that should occure in the database entry of
	 *              that user.
	 * @param uid   The ID of the user whose email adress should be changed.
	 * @return A boolean that shows if the change was successful.
	 */
	@PutMapping("api/users/{uid}/email")
	public UserDTO updateUserEmail(@RequestParam String email, @PathVariable Long uid) {
		User u = userRepository.findById(uid).orElse(null);
		if (u != null) {
			u.setEMailAddress(email);
			userRepository.save(u);
			return new UserDTO(u);
		}
		return null;
	}

//	/**
//	 * This method allows an admin to add new meters to the account of an user. The
//	 * meters are stored as a list in the database entry of the user its belonging
//	 * to.
//	 * 
//	 * @param token    The JWT of the admin to authenticate himself.
//	 * @param meterIDs The list of meters which should be added to the users
//	 *                 account. The list needs to contain at least one meter.
//	 * @param uid      The ID of the user who should become new meters associated
//	 *                 with himself.
//	 * @return A boolean that shows if the operation was successful.
//	 */
//	@PutMapping("api/users/{uid}")
//	public String addMetersToUser(@RequestParam List<Meter> meterIDs, @PathVariable Long uid) {
//		return null;
//	}

	@GetMapping("/api/users/me")
	public UserDTO getOwnData(HttpServletRequest request) {
		return new UserDTO(userRepository.findByUsername(request.getUserPrincipal().getName()));
		// return null;
	}

//	/**
//	 * This method allows an admin to delete meters from an user account. So to
//	 * remove meters from the list of meters associated with this users account
//	 * 
//	 * @param token    The JWT of the admin to authenticate himself.
//	 * @param meterIDs The list of meters which should be deleted from the users
//	 *                 account. The list needs to contain at least one meter.
//	 * @param uid      The ID of the user who should have less meters associated
//	 *                 with his account.
//	 * @return A boolean that shows if the operation was successful.
//	 */
//	@DeleteMapping("api/users/{uid}")
//	public String removeMetersFromUser(@RequestParam List<Meter> meterIDs, @PathVariable Long uid) {
//		return null;
//	}
	@PutMapping("/api/users/me/email")
	public UserDTO updateOwnEmail(HttpServletRequest request, @RequestParam String email) {
		User u = userRepository.findByUsername(request.getUserPrincipal().getName());
		return updateUserEmail(email, u.getPersonID());
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
	@PutMapping("api/users/{uid}/meters")
	public boolean addMetersToUser(@RequestParam List<String> meterIDs, @PathVariable String uid) {

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
	@DeleteMapping("api/users/{uid}/meters")
	public boolean removeMetersFromUser(@RequestParam List<Meter> meterIDs, @PathVariable String uid) {

		return false;
	}
}
