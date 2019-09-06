package de.sopro.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import de.sopro.TestConfig;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfig.class)
public class ReadingControllerTest {

	@Autowired
	MockMvc mvc;

	//@Test
	//@WithAnonymousUser
	public void testUpdateReadingIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to update a reading

		mvc.perform(put("api/meters/{mid}/readings/{rid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

//	@Test
//	@WithMockUser(username = "user", roles = { "USER" }) //hier auch checken ob eigener Zähler?
//	public void testUpdateReadingAsUser() throws Exception {
//		
//		// Check if users without administration rights can update a meter
//		
//		mvc.perform(put("api/meters/{mid}/readings/{rid}")).andExpect(status().is3xxRedirection())
//				.andExpect(redirectedUrlPattern("api/meters/{mid}/readings"));
//	}

	//@Test
	//@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testUpdateReadingAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to update readings

		mvc.perform(put("api/meters/{mid}/readings/{rid}").contentType("applications/json")).andExpect(status().isOk());
	}

	//@Test
	//@WithAnonymousUser
	public void testGetReadingHistoryIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to get the reading history

		mvc.perform(get("api/meters/{mid}/readings/{rid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

	//@Test
	//@WithMockUser(username = "user", roles = { "USER" })
	public void testGetReadingHistoryAsUser() throws Exception {

		// Check if users without administration rights can't get the reading history

		mvc.perform(get("api/meters/{mid}/readings/{rid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("api/meters/{mid}/readings"));
	}

	//@Test
	//@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testGetReadingHistoryAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to get the reading
		// history

		mvc.perform(get("api/meters/{mid}/readings/{rid}").contentType("applications/json")).andExpect(status().isOk());
	}

	//@Test
	//@WithAnonymousUser
	public void testDeleteReadingIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to delete a reading

		mvc.perform(delete("api/meters/{mid}/readings/{rid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

	//@Test
	//@WithMockUser(username = "user", roles = { "USER" })
	public void testDeleteReadingAsUser() throws Exception {

		// Check if users without administration rights can't delete readings

		mvc.perform(delete("api/meters/{mid}/readings/{rid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("api/meters/{mid}/readings"));
	}

	//@Test
	//@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testDeleteReadingAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to delete readings

		mvc.perform(delete("api/meters/{mid}/readings/{rid}").contentType("applications/json"))
				.andExpect(status().isOk());
	}
}
