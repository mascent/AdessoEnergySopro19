package de.sopro.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.sopro.data.Meter;
import de.sopro.data.Person;
import de.sopro.data.Role;
import de.sopro.data.User;
import de.sopro.data.UserMeterAssociation;
import de.sopro.repository.MeterRepository;
import de.sopro.repository.PersonRepository;
import de.sopro.repository.UserMeterAssociationRepository;
import de.sopro.repository.UserRepository;

/**
 * The user controller contains operations manage all requests belonging to user
 * accounts.
 * 
 * @author Mattis
 *
 */
@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	UserMeterAssociationRepository userMeterAssociationRepository;

	@Autowired
	MeterRepository meterRepository;

	/**
	 * This method allows an admin to create a new user in the system. The user
	 * object is created in before, given as a parameter and stored in the database
	 * when this method is executed.
	 *
	 * @param token The JWT of the admin to authenticate himself.
	 * @param user  The object of the user to store in the database.
	 * @return A boolean that shows if the creation was successful.
	 */
	@PostMapping("api/users")
	public String createUser(Jwt token, String name, String surname, String eMailAddress, String userNumber,
			String userName, String password, Role role) {
		if (!name.isEmpty() && !surname.isEmpty() && !eMailAddress.isEmpty() && !userNumber.isEmpty()
				&& !userName.isEmpty() && !password.isEmpty() && password.length() > 7 && password.length() < 51) {
			String creatorId = token.getId(); // hier gucken, wie das geht..
			Person person = personRepository.findById(creatorId);
			if (person.getRole().equals(Role.Admin)) {
				User user = new User(name, surname, eMailAddress, userNumber, userName, password, role);
				String userId = user.getUserId();
				userRepository.save(user);
				return userId;
			}
			return null; // wahrscheinlich lieber Fehler
		}
		return null; // wahrscheinlich lieber Fehler
	}

	/**
	 * This method allows an admin to delete a user out of the system. The user
	 * object is deleted out of the database.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @param uid   The ID of the user that should be deleted.
	 * @return A boolean that shows if the deletion was successful.
	 */
	@DeleteMapping("api/users/{uid}")
	public String deleteUser(Jwt token, @PathVariable String uid) {
		String deleterId = token.getId(); // hier gucken, wie das geht..
		Person person = personRepository.findById(deleterId);
		User user = userRepository.findById(uid);
		if (person.getRole().equals(Role.Admin)) {
			Date date = new Date();
			user.setDeletedAt(date);
		}
	}

	/**
	 * This method allows an admin to change the name of an existing user in the
	 * database.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @param name  The new name that should occure in the database entry of that
	 *              user.
	 * @param uid   The ID of the user whose name should be changed.
	 * @return A boolean that shows if the change was successful.
	 */
	@PutMapping("api/users/{uid}")
	public String updateUserName(@RequestParam Jwt token, @RequestParam String name, @PathVariable String uid) {
		String updaterId = token.getId(); // hier gucken, wie das geht..
		Person person = personRepository.findById(updaterId);
		User user = userRepository.findById(uid);
		if (person.getRole().equals(Role.Admin) && !name.isEmpty()) {
			Date date = new Date();
			user.setUpdatedAt(date);
			user.setName(name);
		}
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
	@PutMapping("api/users/{uid}")
	public String updateUserSurname(@RequestParam Jwt token, @RequestParam String surname, @PathVariable Long uid) {
		String updaterId = token.getId(); // hier gucken, wie das geht..
		Person person = personRepository.findById(updaterId);
		User user = userRepository.findById(uid);
		if (person.getRole().equals(Role.Admin) && !surname.isEmpty()) {
			Date date = new Date();
			user.setUpdatedAt(date);
			user.setSurname(surname);
		}
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
	@PutMapping("api/users/{uid}")
	public String updateUserEmail(@RequestParam Jwt token, @RequestParam String email, @PathVariable Long uid) {
		String updaterId = token.getId(); // hier gucken, wie das geht..
		Person person = personRepository.findById(updaterId);
		User user = userRepository.findById(uid);
		if (person.getRole().equals(Role.Admin) && !email.isEmpty()) {
			Date date = new Date();
			user.setUpdatedAt(date);
			user.setEMailAddress(email);
		}
	}

	/**
	 * This method allows an user to change his email adress which is stored in the
	 * database.
	 * 
	 * @param token The JWT of the user to authenticate himself.
	 * @param name  The new email address that should occure in the database entry
	 *              of that user.
	 * @return A boolean that shows if the change was successful.
	 */
	@PutMapping("api/users/me")
	public String updateOwnEmail(@RequestParam Jwt token, @RequestParam String email) {
		String updaterId = token.getId(); // hier gucken, wie das geht..
		Person person = personRepository.findById(updaterId);
		if (person.getRole().equals(Role.User) && !email.isEmpty()) {
			Date date = new Date();
			User user = (User) person;
			user.setUpdatedAt(date);
			user.setEMailAddress(email);
		}
	}

//Update Address Methode unnötig, da nur Zähler ne Address haben und die nicht umziehen können

	/**
	 * This method allows an admin to change the user number of an existing user in
	 * the database.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @param name  The new user number that should occure in the database entry of
	 *              that user.
	 * @param uid   The ID of the user whose user number should be changed.
	 * @return A boolean that shows if the operation was successful.
	 */
	@PutMapping("api/users/{uid}")
	public String updateUserNumber(@RequestParam Jwt token, @RequestParam String number, @PathVariable String uid) {
		String updaterId = token.getId(); // hier gucken, wie das geht..
		Person person = personRepository.findById(updaterId);
		User user = userRepository.findById(uid);
		if (person.getRole().equals(Role.Admin) && !number.isEmpty()) {
			Date date = new Date();
			user.setUpdatedAt(date);
			user.setUserNumber(number);
		}
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
	@PutMapping("api/users/{uid}")
	public boolean addMetersToUser(@RequestParam Jwt token, @RequestParam List<String> meterIDs, @PathVariable String uid) {
		String personId = token.getId(); // hier gucken, wie das geht..
		Person person = personRepository.findById(personId);
		if (person.getRole().equals(Role.Admin) {
			User user = userRepository.findById(uid);
			for(String m : meterIDs) {
				Meter meter = meterRepository.findById(m);
				UserMeterAssociation newUserMeterA = new UserMeterAssociation(user, meter);
				return true;
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
	@DeleteMapping("api/users/{uid}")
	public boolean removeMetersFromUser(@RequestParam Jwt token, @RequestParam List<Meter> meterIDs,
			@PathVariable String uid) {
		String personId = token.getId(); // hier gucken, wie das geht..
		Person person = personRepository.findById(personId);
		if (person.getRole().equals(Role.Admin) {
			User user = userRepository.findById(uid);
			for(String m : meterIDs) {
				Meter meter = meterRepository.findById(m);
				//hier UserMeterAssociationn getten und löschen
				return true;
			}
		}	
		return false;
	}
}
