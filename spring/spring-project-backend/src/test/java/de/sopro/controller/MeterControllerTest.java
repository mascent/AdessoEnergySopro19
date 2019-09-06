package de.sopro.controller;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import de.sopro.TestConfig;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfig.class)
public class MeterControllerTest {

	@Autowired
	MockMvc mvc;

//	@Test
//	@WithAnonymousUser
	public void testAddReadingIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to add a reading to a meter

		mvc.perform(post("/api/meters/{mid}/readings")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

//	@Test //User nur bei eigenem aber wie????
//	@WithMockUser(username = "user", roles = { "USER" })
//	public void testAddReadingAsUser() throws Exception {

	// Check if users without administration rights can only add readings to their
	// own meter

//		mvc.perform(post("/api/meters/{mid}/readings"));
//	}

//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testAddReadingAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to add a new reading

		mvc.perform(post("/api/meters/{mid}/readings").contentType("applications/json")).andExpect(status().isOk());
	}

//	@Test
//	@WithAnonymousUser
	public void testGetReadingsIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to get all readings belonging to a meter

		mvc.perform(get("/api/meters/{mid}/readings")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

//	@Test //User nur bei eigenem aber wie????
//	@WithMockUser(username = "user", roles = { "USER" })
//	public void testGetReadingsAsUser() throws Exception {

	// Check if users without administration rights can only get readings of their
	// own meters

//		mvc.perform(get("/api/meters/{mid}/readings"));
//	}

//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testGetReadingsAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to get a reading

		mvc.perform(get("/api/meters/{mid}/readings").contentType("applications/json")).andExpect(status().isOk());
	}

//	@Test
//	@WithAnonymousUser
	public void testCreateMeterIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to create a meter

		mvc.perform(post("api/meters")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

//	@Test
//	@WithMockUser(username = "user", roles = { "USER" })
	public void testCreateMeterAsUser() throws Exception {

		// Check if users without administration rights can't create a new meter

		mvc.perform(post("api/meters")).andExpect(status().is3xxRedirection());
	}

//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testCreateMeterAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to create a new meter

		mvc.perform(post("api/meters").contentType("applications/json")).andExpect(status().isOk());
	}

//	@Test
//	@WithAnonymousUser
	public void testUpdateMeterIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to update a meter

		mvc.perform(put("/api/meters/{mid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

//	@Test
//	@WithMockUser(username = "user", roles = { "USER" })
	public void testUpdateMeterAsUser() throws Exception {

		// Check if users without administration rights can't update a meter

		mvc.perform(put("/api/meters/{mid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("/api/meters"));
	}

//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testUpdateMeterAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to update a meter

		mvc.perform(put("/api/meters/{mid}").contentType("applications/json")).andExpect(status().isOk());
	}

//	@Test
//	@WithAnonymousUser
	public void testDeleteMeterIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to delete a meter

		mvc.perform(delete("/api/meters/{mid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

//	@Test
//	@WithMockUser(username = "user", roles = { "USER" })
	public void testDeleteMeterAsUser() throws Exception {

		// Check if users without administration rights can't delete a meter

		mvc.perform(delete("/api/meters/{mid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("/api/meters"));
	}

//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testDeleteMeterAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to delete a meter

		mvc.perform(delete("/api/meters/{mid}").contentType("applications/json")).andExpect(status().isOk());
	}

//	@Test
//	@WithAnonymousUser
	public void testGetAllMetersIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to get a list of all meters existing in the system

		mvc.perform(get("/api/meters")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

//	@Test
//	@WithMockUser(username = "user", roles = { "USER" })
	public void testGetAllMetersAsUser() throws Exception {

		// Check if users without administration rights can't get a list of all meters
		// existing

		mvc.perform(get("/api/meters")).andExpect(status().is3xxRedirection());
	}

//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testGetAllMetersAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to get all meters.

		mvc.perform(get("/api/meters").contentType("applications/json")).andExpect(status().isOk());
	}
//
//	@Test
//	@WithAnonymousUser

	// Check if anonymous users are redirected to the login page when
	// trying to get a meter by its ID

	public void testGetMeterByIDIfNotLoggedIn() throws Exception {
		mvc.perform(get("/api/meters/{mid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

//	@Test //nur bei eigenem Meter, wie überprüfen?
//	@WithMockUser(username = "user", roles = { "USER" })
//	public void testGetMeterByIDAsUser() throws Exception {

	// Check if users without administration rights can only get their own meters
	// by their ID

//		mvc.perform(get("/api/meters/{mid}").contentType("applications/json")).andExpect(status().isOk());
//	}

//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testGetMeterByIDAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to get a meter by its
		// id

		mvc.perform(get("/api/meters/{mid}").contentType("applications/json")).andExpect(status().isOk());
	}

}
