package de.sopro.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.sopro.data.Issue;
import de.sopro.data.Person;
import de.sopro.data.Role;
import de.sopro.data.User;
import de.sopro.dto.IssueDTO;
import de.sopro.repository.IssueRepository;
import de.sopro.repository.PersonRepository;
import de.sopro.repository.UserRepository;
import okhttp3.Credentials;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IssueControllerTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	IssueRepository issueRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	Issue i;
	Long testIssueId;
	User user;
	Person admin;
	String userCredentials;
	String adminCredentials;

	@BeforeEach
	public void createEntrys() {
		String userPassword = "password";
		String adminPassword = "password";
		if (userRepository.findByUsername("fwm").orElse(null) != null) {
			userRepository.deleteById(userRepository.findByUsername("fwm").orElse(null).getPersonId());
		}

		user = new User("frank", "white", "frank@white.mercedes", "fwm", passwordEncoder.encode(userPassword),
				Role.User);
		userRepository.save(user);

		if (personRepository.findByUsername("fler").orElse(null) != null) {
			personRepository.deleteById(personRepository.findByUsername("fler").orElse(null).getPersonId());
		}

		admin = new Person("fler", passwordEncoder.encode(adminPassword), Role.Admin);
		personRepository.save(admin);

		i = new Issue("fwm", "frank@white.mercedes", "Flizzy", "Warum denn Mercedes?", user.getPersonId());
		issueRepository.save(i);
		testIssueId = i.getIssueId();

		userCredentials = Credentials.basic("fwm", userPassword);
		adminCredentials = Credentials.basic("fler", adminPassword);
	}

	@AfterEach
	public void removeEntrys() {
		issueRepository.deleteAll();
		if (user != null) {
			userRepository.delete(user);
		}
		if (admin != null) {
			personRepository.delete(admin);
		}
	}

	@Test
	@WithAnonymousUser
	public void testCreateIssueIfNotLoggedIn() throws Exception {

		// Check if users who are not logged in can't create a new issue

		mvc.perform(post("/api/issues")).andExpect(status().is4xxClientError());
	}

	@Test
//	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testCreateIssueAsUser() throws Exception {

		// Check if users without administration rights can create a new issue

		MvcResult result = mvc
				.perform(post("/api/issues").header(HttpHeaders.AUTHORIZATION, userCredentials).param("name", "fw37")
						.param("email", "frank-white@bmw.com").param("subject", "Frank White")
						.param("description", "jagt euch mit dem BMW BMW.").contentType("applications/json"))
				.andExpect(status().isOk()).andReturn();

		// cleaning up by deleting the new created issue

		String s = result.getResponse().getContentAsString();
		IssueDTO issue = new Gson().fromJson(s, IssueDTO.class);
		assertEquals(issue.getSubject(), "Frank White");
		if (issue != null) {
			issueRepository.deleteById(issue.getId());
		}
	}

	@Test
//	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testCreateIssueAsUserWithMissingEmail() throws Exception {

		// Check if users without administration rights can't create a new issue with
		// missing arguments

		MvcResult result = mvc.perform(post("/api/issues").header(HttpHeaders.AUTHORIZATION, userCredentials)
				.param("name", "fw37").param("subject", "Frank White")
				.param("description", "jagt euch mit dem BMW BMW.").contentType("applications/json"))
				.andExpect(status().is4xxClientError()).andReturn();

		// check if issue isn't existing

		IssueDTO issue = new Gson().fromJson(result.getResponse().getContentAsString(), IssueDTO.class);
		assertEquals(issue, null);
	}

	@Test
//	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
	public void testCreateIssueAsAdmin() throws Exception {

		// Check if administrators can't create a new issue because there is no reason
		// they would ever do this

		mvc.perform(post("/api/issues").header(HttpHeaders.AUTHORIZATION, adminCredentials))
				.andExpect(status().is4xxClientError());
	}

	@Test
	@WithAnonymousUser
	public void testGetIssuesIfNotLoggedIn() throws Exception {

		// Check if users who are not logged in can't get a list of all issues

		mvc.perform(get("/api/issues")).andExpect(status().is4xxClientError());
	}

	@Test
//	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testGetIssuesAsUser() throws Exception {

		// Check if users without administration rights can't get a list of all issues

		mvc.perform(get("/api/issues").header(HttpHeaders.AUTHORIZATION, userCredentials))
				.andExpect(status().is4xxClientError());
	}

	@Test
//	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
	public void testGetIssuesAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to get a list of all
		// issues

		MvcResult result = mvc.perform(
				get("/api/issues").header(HttpHeaders.AUTHORIZATION, adminCredentials).contentType("applications/json"))
				.andExpect(status().isOk()).andReturn();
//		Type listType = new TypeToken<List<IssueDTO>>() {}.getType();
		String s = result.getResponse().getContentAsString();
		List<IssueDTO> issues = new Gson().fromJson(s, new TypeToken<List<IssueDTO>>() {}.getType());
		assertEquals(issues.get(0).getId(), new IssueDTO(i).getId());
	}

	@Test
	@WithAnonymousUser
	public void testCloseIssueIfNotLoggedIn() throws Exception {

		// Check if user who are not logged in can't close an issue

		mvc.perform(delete("/api/issues" + testIssueId)).andExpect(status().is4xxClientError());
	}

	@Test
//	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testCloseIssueAsUser() throws Exception {

		// Check if users without administration rights can't close an issue

		mvc.perform(delete("/api/issues" + testIssueId).header(HttpHeaders.AUTHORIZATION, userCredentials))
				.andExpect(status().is4xxClientError());
	}

	@Test
//	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
	public void testCloseIssueAsAdmin() throws Exception {

		// CHeck if users with administration rights can close an issue

		MvcResult result = mvc
				.perform(delete("/api/issues/" + testIssueId).header(HttpHeaders.AUTHORIZATION, adminCredentials)
						.param("iid", testIssueId.toString()).contentType("applications/json"))
				.andExpect(status().isOk()).andReturn();
		Boolean succes = new Gson().fromJson(result.getResponse().getContentAsString(), Boolean.class);
		assertEquals(succes, true);
		Issue i1 = issueRepository.findById(testIssueId).orElse(null);
		assertEquals(i1.getCloserId(), admin.getPersonId());
	}

	@Test
	@WithAnonymousUser
	public void testGetIssueByIDIfNotLoggedIn() throws Exception {

		// Check if who are not logged in can't get an issue by it's ID

		mvc.perform(get("/api/issues/" + testIssueId.toString())).andExpect(status().is4xxClientError());
	}

	@Test
//	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testGetIssueByIDAsUser() throws Exception {

		// Check if users without administration rights can't get an issue by its ID

		mvc.perform(get("/api/issues" + testIssueId.toString()).header(HttpHeaders.AUTHORIZATION, userCredentials))
				.andExpect(status().is4xxClientError());
	}

	@Test
//	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
	public void testGetIssueByIDsAdmin() throws Exception {

		// Check if users with administration rights can get an issue by its ID

		MvcResult result = mvc
				.perform(
						get("/api/issues/" + testIssueId.toString()).header(HttpHeaders.AUTHORIZATION, adminCredentials)
								.param("iid", testIssueId.toString()).contentType("applications/json"))
				.andExpect(status().isOk()).andReturn();
		IssueDTO issue = new Gson().fromJson(result.getResponse().getContentAsString(), IssueDTO.class);
		assertEquals(issue.getName(), "fwm");
		assertEquals(issue.getEmail(), "frank@white.mercedes");
		assertEquals(issue.getSubject(), "Flizzy");
		assertEquals(issue.getDescription(), "Warum denn Mercedes?");
		assertEquals(issue.getIsClosed(), false);
	}

}
