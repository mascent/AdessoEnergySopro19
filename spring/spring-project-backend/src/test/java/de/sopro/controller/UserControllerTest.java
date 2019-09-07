package de.sopro.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import de.sopro.data.User;
import de.sopro.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	UserRepository userRepository;

	@Test
	@WithAnonymousUser
	public void testCreateUserIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to create a new user

		mvc.perform(get("/api/users")).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "user", roles = { "User" })
	public void testCreateUserAsUser() throws Exception {

		// Check if users without administration rights can't create a new user

		mvc.perform(post("/api/users")).andExpect(status().is4xxClientError());
	}

//	@Test
//	@WithMockUser(username = "admin", roles = { "Admin" })
	public void testCreateUserAsAdmin() throws Exception {

		User frank = userRepository.findByUsername("fw37");
		if (frank != null) {

			userRepository.delete(frank);
		}

		mvc.perform(post("/api/users").param("firstName", "frank").param("lastName", "white")
				.param("eMailAddress", "frank-white@bmw.com").param("customerNumber", "fw37")
				.param("password", "password").contentType("applications/json")).andExpect(status().isOk());

		// delte user so we can reuse it the next time
		frank = userRepository.findByUsername("fw37");
		userRepository.delete(frank);

	}

	@Test
	@WithMockUser(username = "admin", roles = { "Admin" })
	public void testCreateUserWithToFewArguments() throws Exception {

		User frank = userRepository.findByUsername("fw37");
		if (frank != null) {

			userRepository.delete(frank);
		}
		mvc.perform(post("/api/users").param("firstName", "frank").param("eMailAddress", "frank-white@bmw.com")
				.param("customerNumber", "fw37").param("password", "password").contentType("applications/json"))
				.andExpect(status().is4xxClientError());

		// delte user so we can reuse it the next time
		frank = userRepository.findByUsername("fw37");

		assertEquals(frank, null);
	}

	// @Test
	// @WithAnonymousUser
	public void testDeleteUserIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to delete an existing user

		mvc.perform(delete("/api/users/{uid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

	// @Test
	// @WithMockUser(username = "user", roles = { "USER" })
	public void testDeleteUserAsUser() throws Exception {

		// Check if users without administration rights can't delete users

		mvc.perform(delete("/api/users/{uid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("/api/users"));
	}

	// @Test
	// @WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testDeleteUserAsAdmin() throws Exception {
		mvc.perform(delete("/api/users/{uid}").contentType("applications/json")).andExpect(status().isOk());
	}

	// @Test
	// @WithAnonymousUser
	public void testChangeUserNameIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to change the name or other details of an user

		mvc.perform(put("/api/users/{uid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

	// @Test
	// @WithMockUser(username = "user", roles = { "USER" })
	public void testChangeUserNameAsUser() throws Exception {

		// Check if users without administration rights can't change user names

		mvc.perform(put("/api/users/{uid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("/api/users"));
	}

	// @Test
	// @WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testChangeUserNameAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to change user names

		mvc.perform(put("/api/users/{uid}").contentType("applications/json")).andExpect(status().isOk());
	}

	// @Test
	// @WithAnonymousUser
	public void testChangeOwnEmailIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to change own email

		mvc.perform(put("api/users/me")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

//	@Test //bei beiden checken, obs eigene Email ist
//	@WithMockUser(username = "user", roles = { "USER" })
//	public void testChangeOwnEmailAsUser() throws Exception {

	// Check if users without administration rights can change its own email address

//		mvc.perform(put("api/users/me").contentType("applications/json")).andExpect(status().isOk());
//	}
//	
//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
//	public void testChangeOwnEmailAsAdmin() throws Exception {

	// Check if users with administration rights are allowed to change their email
	// address

//		mvc.perform(put("api/users/me").contentType("applications/json")).andExpect(status().isOk());
//	}
}
