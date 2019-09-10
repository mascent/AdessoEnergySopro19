package de.sopro.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.MvcResult;

import com.google.gson.Gson;

import de.sopro.TestConfig;
import de.sopro.data.Issue;
import de.sopro.data.Role;
import de.sopro.repository.IssueRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IssueControllerTest {

	Long testIssueId = (long) 0;

	@Autowired
	MockMvc mvc;

	@Autowired
	IssueRepository issueRepository;

	@BeforeEach
	public void setUp() {
		try {
			MvcResult result = mvc.perform(post("/api/issues").param("name", "mts").param("email", "mattists97@web.de")
					.param("subject", "Test").param("description", "Hallo. Hallo. Test. Test.")
					.contentType("applications/json")).andReturn();
			Issue issue = new Gson().fromJson(result.getResponse().getContentAsString(), Issue.class);
			testIssueId = issue.getIssueId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@WithAnonymousUser
	public void testCreateIssueIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to create an issue

		mvc.perform(post("/api/issues")).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "user", roles = { "User" })
	public void testCreateIssueAsUser() throws Exception {

		// Check if users without administration rights can create a new issue

		MvcResult result = mvc.perform(post("/api/issues").param("name", "fw37").param("email", "frank-white@bmw.com")
				.param("subject", "Frank White").param("description", "jagt dich mit dem BMW.")
				.contentType("applications/json")).andExpect(status().isOk()).andReturn();

		// cleaning up by deleting the new created issue

		Issue issue = new Gson().fromJson(result.getResponse().getContentAsString(), Issue.class);
		issueRepository.delete(issue);
	}

	@Test
	@WithMockUser(username = "user", roles = { "User" })
	public void testCreateIssueAsUserWithMissingEmail() throws Exception {

		// Check if users without administration rights can create a new issue

		MvcResult result = mvc
				.perform(post("/api/issues").param("name", "fw37").param("subject", "Frank White")
						.param("description", "jagt dich mit dem BMW.").contentType("applications/json"))
				.andExpect(status().is4xxClientError()).andReturn();

		// cleaning up by deleting the new created issue
		Issue issue = new Gson().fromJson(result.getResponse().getContentAsString(), Issue.class);
		assertEquals(issue, null);
	}

	@Test
	@WithMockUser(username = "admin", roles = { "Admin" })
	public void testCreateIssueAsAdmin() throws Exception {

		// Check if administrators can't create a new issue because there is no reason
		// they would ever do this
		mvc.perform(post("/api/issues")).andExpect(status().is4xxClientError());
	}
	
	@Test
	@WithAnonymousUser
	public void testGetIssuesIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to get all issues

		mvc.perform(get("/api/issues")).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "user", roles = { "User" })
	public void testGetIssuesAsUser() throws Exception {

		// Check if users without administration rights can't get a list of all issues

		mvc.perform(get("/api/issues")).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "admin", roles = { "Admin" })
	public void testGetIssuesAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to get all issues

		MvcResult result = mvc.perform(get("/api/issues").contentType("applications/json")).andExpect(status().isOk())
				.andReturn();
		Iterable<Issue> issues = new Gson().fromJson(result.getResponse().getContentAsString(), Issue.class);	
	}

	@Test
	@WithAnonymousUser
	public void testCloseIssueIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to delete an issue

		mvc.perform(delete("/api/issues" + testIssueId.toString())).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "user", roles = { "User" })
	public void testCloseIssueAsUser() throws Exception {

		// Check if users without administration rights can't delete an issue

		mvc.perform(delete("/api/issues" + testIssueId.toString())).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "admin", roles = { "Admin" })
	public void testCloseIssueAsAdmin() throws Exception {
		mvc.perform(delete("/api/issues/" + testIssueId.toString()).contentType("applications/json"))
				.andExpect(status().isOk());
		Issue issue = issueRepository.findById(testIssueId).orElse(null);
		assertFalse(issue.getCloserId().equals(null));
	}

	@Test
	@WithAnonymousUser
	public void testGetIssueByIDIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to get an issue by its ID

		mvc.perform(get("/api/issues/" + testIssueId.toString())).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "user", roles = { "User" })
	public void testGetIssueByIDAsUser() throws Exception {

		// Check if users without administration rights can't get an issue by its ID

		mvc.perform(get("/api/issues" + testIssueId.toString())).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "admin", roles = { "Admin" })
	public void testGetIssueByIDsAdmin() throws Exception {
		MvcResult result = mvc.perform(get("/api/issues/" + testIssueId.toString()).contentType("applications/json"))
				.andExpect(status().isOk()).andReturn();
		Issue issue = new Gson().fromJson(result.getResponse().getContentAsString(), Issue.class);
		assertEquals(issue.getName(), "mts");
		assertEquals(issue.getEmail(), "mattists97@web.de");
		assertEquals(issue.getSubject(), "Test");
		assertEquals(issue.getDescription(), "Hallo. Hallo. Test. Test");
		assertEquals(issue.getCloserId(), null);
	}

}
