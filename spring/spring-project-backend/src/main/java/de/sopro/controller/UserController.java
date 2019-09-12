package de.sopro.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.sopro.data.Person;
import de.sopro.data.Role;
import de.sopro.data.User;
import de.sopro.dto.UserDTO;
import de.sopro.repository.MeterRepository;
import de.sopro.repository.PersonRepository;
import de.sopro.repository.UserMeterAssociationRepository;
import de.sopro.repository.UserRepository;
import de.sopro.util.exception.ResourceNotFoundException;

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
	 * @throws Exception
	 */
	@PostMapping("/api/users")
	@CrossOrigin
	public UserDTO createUser(@RequestBody UserDTO userDTO) throws Exception {

		if (userRepository.findByUsername(userDTO.getCustomerNumber()).orElse(null) != null
				|| userRepository.findByEMailAddress(userDTO.getEmail()).orElse(null) != null) {
			return null;
		}
		User u;
		try {
			u = userRepository.save(new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
					userDTO.getCustomerNumber(), passwordEncoder.encode(userDTO.getPassword()), Role.User));
		} catch (Exception e) {
			throw new Exception();
		}

		return new UserDTO(u);

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
	public UserDTO updateUser(HttpServletRequest request, @PathVariable Long uid, @RequestBody UserDTO userDTO)
			throws Exception {
		Person p = personRepository.findByUsername(request.getUserPrincipal().getName()).orElse(null);
		User u = userRepository.findById(uid).orElseThrow(() -> new ResourceNotFoundException());
		if (p.getRole().equals(Role.Admin)) {
			u.setEMailAddress(userDTO.getEmail());
			u.setFirstName(userDTO.getFirstName());
			u.setLastName(userDTO.getLastName());
		} else if (p.getPersonId().equals(u.getPersonId())) {
			u.setEMailAddress(userDTO.getEmail());
		} else {
			throw new Exception();
		}
		return new UserDTO(userRepository.save(u));
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
	 * @throws Exception
	 */
	@PutMapping("/api/users/me")
	@CrossOrigin
	public UserDTO updateOwnEmail(HttpServletRequest request, @RequestBody UserDTO userDTO) throws Exception {
		User u = userRepository.findByUsername(request.getUserPrincipal().getName()).orElse(null);
		return updateUser(request, u.getPersonId(), userDTO);
	}

}
