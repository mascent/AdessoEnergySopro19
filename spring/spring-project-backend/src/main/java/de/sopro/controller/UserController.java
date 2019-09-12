package de.sopro.controller;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.sopro.data.Role;
import de.sopro.data.User;
import de.sopro.dto.UserDTO;
import de.sopro.repository.MeterRepository;
import de.sopro.repository.PersonRepository;
import de.sopro.repository.UserMeterAssociationRepository;
import de.sopro.repository.UserRepository;
import de.sopro.util.exception.ResourceNotFoundException;
import kotlin.NoWhenBranchMatchedException;

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

	/**
	 * Create a list of basic-users and show to a admin-user. Can only be called
	 * when sufficient authentication is called.
	 * 
	 * @return A itterable UserList;
	 */
	@GetMapping("/api/users")
	@CrossOrigin
	public Iterable<UserDTO> getUsers() {
		return StreamSupport.stream(userRepository.findAll().spliterator(), false).map(u -> new UserDTO(u))
				.collect(Collectors.toList());
	}

	/**
	 * 
	 * This method allows an admin-user to create a new basic-user in the system.
	 * The password is not saved in plain text, but rather encoded using the
	 * PasswordEncoderBean.
	 * 
	 * @param firstName      The first name of the new user.
	 * @param lastName       The last name of the new user.
	 * @param eMailAdress    The eMail-Address of the new user.
	 * @param customerNumber The customerNumber given by Adesso. Also doubles as
	 *                       username for basic-users.
	 * @param password       The inital password for the new user.
	 * @return A UserDTO to represent the saved user.
	 */
	@PostMapping(path = "/api/users", params = { "firstName", "lastName", "eMailAddress", "customerNumber",
			"password" })
	@CrossOrigin
	public UserDTO createUser(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String eMailAddress, @RequestParam String customerNumber, @RequestParam String password) {

		if (userRepository.findByUsername(customerNumber) != null
				|| userRepository.findByEMailAddress(eMailAddress) != null) {
			return null;
		}
		User u;
		try {
			u = userRepository.save(new User(firstName, lastName, eMailAddress, customerNumber,
					passwordEncoder.encode(password), Role.User));
		} catch (Exception e) {
			return null;
		}

		return new UserDTO(u);

	}

	/**
	 * Create user using a userDTO
	 * 
	 * @param userDTO  The userDTO with all information needed.
	 * @param password the password for the new user.
	 * @return A UserDTO to represent the saved user.
	 */
	@PostMapping(path = "/api/users", params = { "userDTO", "password" })
	@CrossOrigin
	public UserDTO createUser(UserDTO userDTO, String password) {
		return createUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
				userDTO.getCustomerNumber(), password);
	}

	/**
	 * This method allows an admin to delete a user out of the system. The user
	 * object is deleted out of the database.
	 * 
	 * @param uid The user-ID of the user that should be deleted.
	 * @return A boolean that shows if the deletion was successful.
	 */
	@DeleteMapping("/api/users")
	@CrossOrigin
	public boolean deleteUser(@RequestParam Long uid) {

		if (userRepository.existsById(uid)) {
			userRepository.deleteById(uid);
			return true;
		}

		return false;
	}

	@PutMapping("/api/users/{uid}")
	@CrossOrigin
	public UserDTO updateUser(@PathVariable Long uid, @RequestParam UserDTO userDTO) throws ResourceNotFoundException {
		User u = userRepository.findById(uid).orElseThrow(() -> new ResourceNotFoundException());
		u.setEMailAddress(userDTO.getEmail());
		u.setFirstName(userDTO.getFirstName());
		u.setLastName(userDTO.getLastName());
		return new UserDTO(userRepository.save(u));
	}

	/**
	 * This method allows an admin to change the surname of an existing user in the
	 * database.
	 * 
	 * @param name The new surname that should be stored in the database entry of
	 *             the specified user.
	 * @param uid  The ID of the user whose surname should be changed.
	 * @return A boolean that shows if the change was successful.
	 */
	@PutMapping("/api/users/{uid}/surname")
	@CrossOrigin
	public UserDTO updateUserSurname(@RequestParam String object, @RequestParam String surname,
			@PathVariable Long uid) {
		User u = userRepository.findById(uid).orElse(null);
		if (u == null) {
			return null;
		}
		u.setLastName(surname);
		u.setUpdatedAt(LocalDateTime.now());
		userRepository.save(u);
		return new UserDTO(u);
	}

	/**
	 * This method allows an admin to change the email address of an existing user
	 * in the database.
	 * 
	 * @param name The new email address that should be saved in the database entry
	 *             of the specified user.
	 * @param uid  The ID of the user whose email address should be changed.
	 * @return A UserDTO representing the updated user or {@code null} when the
	 *         email-Address is already in the database or no such user exists.
	 */
	@PutMapping("/api/users/{uid}/email")
	@CrossOrigin
	public UserDTO updateUserEmail(@RequestParam String email, @PathVariable Long uid) {
		User u = userRepository.findById(uid).orElse(null);
		if (u == null || userRepository.findByEMailAddress(email) != null) {
			return null;
		}
		u.setEMailAddress(email);
		u.setUpdatedAt(LocalDateTime.now());
		userRepository.save(u);
		return new UserDTO(u);
	}

	@GetMapping("/api/users/me")
	@CrossOrigin
	public UserDTO getOwnData(HttpServletRequest request) {
		return new UserDTO(userRepository.findByUsername(request.getUserPrincipal().getName()).orElse(null));
		// return null;
	}

	/**
	 * Lets a user change his own email-address.
	 * 
	 * @param request The Http-Request, it's used to identify the user.
	 * @param email   The new eMail-Address.
	 * @return A UserDTO representing the updated user or {@code null} when the
	 *         email-Address is already in the database.
	 */
	@PutMapping("/api/users/me/email")
	@CrossOrigin
	public UserDTO updateOwnEmail(HttpServletRequest request, @RequestParam String email) {
		User u = userRepository.findByUsername(request.getUserPrincipal().getName()).orElse(null);
		return updateUserEmail(email, u.getPersonId());
	}

}
