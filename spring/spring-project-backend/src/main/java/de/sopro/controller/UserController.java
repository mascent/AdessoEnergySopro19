package de.sopro.controller;

import java.util.List;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
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
import de.sopro.repository.UserRepository;

/**
 * The user controller contains operations manage all requests belonging to user
 * accounts.
 * 
 * @author Mattis
 *
 */
@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/api/users")
	public String getUsers() {
		return "test";
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
	public User createUser(@RequestParam String name, @RequestParam String surname, @RequestParam String eMailAdress,
			@RequestParam String userNumber, @RequestParam String uname, @RequestParam String pwd) {
		System.out.println("test");
		return userRepository.save(new User(name, surname, eMailAdress, userNumber, uname, pwd, Role.User));
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
	public String deleteUser(@PathVariable Long uid) {
		return null;
	}

//	/**
//	 * This method allows an admin to change the name of an existing user in the
//	 * database.
//	 * 
//	 * @param token The JWT of the admin to authenticate himself.
//	 * @param name  The new name that should occure in the database entry of that
//	 *              user.
//	 * @param uid   The ID of the user whose name should be changed.
//	 * @return A boolean that shows if the change was successful.
//	 */
//	@PutMapping("api/users/{uid}")
//	public String updateUserName(@RequestParam String name, @PathVariable Long uid) {
//		return null;
//	}

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
	public String updateUserSurname(@RequestParam String object, @RequestParam String name, @PathVariable Long uid) {
		return null;
	}
//
//	/**
//	 * This method allows an admin to change the email adress of an existing user in
//	 * the database.
//	 * 
//	 * @param token The JWT of the admin to authenticate himself.
//	 * @param name  The new email adress that should occure in the database entry of
//	 *              that user.
//	 * @param uid   The ID of the user whose email adress should be changed.
//	 * @return A boolean that shows if the change was successful.
//	 */
//	@PutMapping("api/users/{uid}")
//	public String updateUserEmail(@RequestParam String email, @PathVariable Long uid) {
//		return null;
//	}

	/**
	 * This method allows an user to change his email adress which is stored in the
	 * database
	 * 
	 * @param token The JWT of the user to authenticate himself.
	 * @param name  The new email address that should occure in the database entry
	 *              of that user.
	 * @return A boolean that shows if the change was successful.
	 */
	@PutMapping("api/users/me")
	public String updateOwnEmail(@RequestParam String name) {
		return null;
	}

//	/**
//	 * This method allows an admin to change the address of an existing user in the
//	 * database.
//	 * 
//	 * @param token The JWT of the admin to authenticate himself.
//	 * @param name  The new address that should occure in the database entry of that
//	 *              user.
//	 * @param uid   The ID of the user whose address should be changed.
//	 * @return A boolean that shows if the operation was successful.
//	 */
//	@PutMapping("api/users/{uid}")
//	public String updateUserAddress(@RequestParam Address address, @PathVariable Long uid) {
//		return null;
//	}

//	/**
//	 * This method allows an admin to change the user number of an existing user in
//	 * the database.
//	 * 
//	 * @param token The JWT of the admin to authenticate himself.
//	 * @param name  The new user number that should occure in the database entry of
//	 *              that user.
//	 * @param uid   The ID of the user whose user number should be changed.
//	 * @return A boolean that shows if the operation was successful.
//	 */
//	@PutMapping("api/users/{uid}")
//	public String updateUserNumber(@RequestParam String number, @PathVariable Long uid) {
//		return null;
//	}

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
}
