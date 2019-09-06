package de.sopro.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class IssueControllerTest {

	@Autowired
	MockMvc mvc;


//	@Test
//	@WithAnonymousUser
	public void testCreateIssueIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to create an issue

		mvc.perform(post("/api/issues")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

//	@Test
//	@WithMockUser(username = "user", roles = { "USER" })
	public void testCreateIssueAsUser() throws Exception {

		// Check if users without administration rights can create a new issue

		mvc.perform(post("/api/issues").contentType("applications/json")).andExpect(status().isOk());
	}
//
//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testCreateIssueAsAdmin() throws Exception { // Admins sollten imo keine Tickets erstellen
		mvc.perform(post("/api/issues")).andExpect(status().is3xxRedirection());
	}
//
//	@Test
//	@WithAnonymousUser
	public void testDeleteIssueIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to delete an issue

		mvc.perform(delete("/api/issues/{iid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}
//
//	@Test
//	@WithMockUser(username = "user", roles = { "USER" })
	public void testDeleteIssueAsUser() throws Exception {

		// Check if users without administration rights can't delete an issue

		mvc.perform(delete("/api/issues/{iid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("/api/issues"));
	}
//
//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testDeleteIssueAsAdmin() throws Exception {
		mvc.perform(delete("/api/issues/{iid}").contentType("applications/json")).andExpect(status().isOk());
	}
//
//	@Test
//	@WithAnonymousUser
	public void testGetIssueByIDIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to get an issue by its ID

		mvc.perform(get("/api/issues/{iid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}
//
//	@Test
//	@WithMockUser(username = "user", roles = { "USER" })
	public void testGetIssueByIDAsUser() throws Exception {

		// Check if users without administration rights can't get an issue by its ID

		mvc.perform(get("/api/issues/{iid}")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("/api/issues"));
	}
//
//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testGetIssueByIDsAdmin() throws Exception {
		mvc.perform(get("/api/issues/{iid}").contentType("applications/json")).andExpect(status().isOk());
	}
//
//	@Test
//	@WithAnonymousUser
	public void testGetIssuesIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to get all issues

		mvc.perform(get("/api/issues")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}
//
//	@Test
//	@WithMockUser(username = "user", roles = { "USER" })
	public void testGetIssuesAsUser() throws Exception {

		// Check if users without administration rights can't get a list of all issues

		mvc.perform(get("/api/issues")).andExpect(status().is3xxRedirection());
	}
//
//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testGetIssuesAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to get all issues

		mvc.perform(get("/api/issues").contentType("applications/json")).andExpect(status().isOk());
	}

}
