package de.sopro.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.sopro.data.Issue;
import de.sopro.dto.IssueDTO;
import de.sopro.repository.IssueRepository;
import de.sopro.repository.PersonRepository;
import de.sopro.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IssueControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	UserRepository userRepository;

	@MockBean
	PersonRepository personRepository;

	@Autowired
	IssueRepository issueRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	Issue i;
	Long testIssueId;

	@BeforeEach
	public void createEntrys() {
		i = new Issue("mts", "mattists97@web.de", "Test", "Hallo. Hallo. Test. Test.", (long) 1);
		issueRepository.save(i);
		testIssueId = i.getIssueId();
	}

	@AfterEach
	public void removeEntrys() {
		if (i != null) {
			issueRepository.delete(i);
		}
	}

	@Test
	@WithAnonymousUser
	public void testCreateIssueIfNotLoggedIn() throws Exception {

		// Check if users who are not logged in can't create a new issue

		mvc.perform(post("/api/issues")).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testCreateIssueAsUser() throws Exception {

		// Check if users without administration rights can create a new issue

		MvcResult result = mvc.perform(post("/api/issues").param("name", "fw37").param("email", "frank-white@bmw.com")
				.param("subject", "Frank White").param("description", "jagt euch mit dem BMW BMW.")
				.contentType("applications/json")).andExpect(status().isOk()).andReturn();

		// cleaning up by deleting the new created issue

		String s = result.getResponse().getContentAsString();
		IssueDTO issue = new Gson().fromJson(s, IssueDTO.class);
		assertEquals(issue.getSubject(), "Frank White"); // TODO fix nullpointer, ist leer weil s leer ist
		if (issue != null) {
			issueRepository.deleteById(issue.getId());
		}
	}

	@Test
	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testCreateIssueAsUserWithMissingEmail() throws Exception {

		// Check if users without administration rights can't create a new issue with
		// missing arguments

		MvcResult result = mvc
				.perform(post("/api/issues").param("name", "fw37").param("subject", "Frank White")
						.param("description", "jagt euch mit dem BMW BMW.").contentType("applications/json"))
				.andExpect(status().is4xxClientError()).andReturn();

		// check if issue isn't existing

		IssueDTO issue = new Gson().fromJson(result.getResponse().getContentAsString(), IssueDTO.class);
		assertEquals(issue, null);
	}

	@Test
	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
	public void testCreateIssueAsAdmin() throws Exception {

		// Check if administrators can't create a new issue because there is no reason
		// they would ever do this

		mvc.perform(post("/api/issues")).andExpect(status().is4xxClientError());
	}

	@Test
	@WithAnonymousUser
	public void testGetIssuesIfNotLoggedIn() throws Exception {

		// Check if users who are not logged in can't get a list of all issues

		mvc.perform(get("/api/issues")).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testGetIssuesAsUser() throws Exception {

		// Check if users without administration rights can't get a list of all issues

		mvc.perform(get("/api/issues")).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
	public void testGetIssuesAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to get a list of all
		// issues

		MvcResult result = mvc.perform(get("/api/issues").contentType("applications/json")).andExpect(status().isOk())
				.andReturn();
		Type listType = new TypeToken<List<IssueDTO>>() {}.getType();
		String s = result.getResponse().getContentAsString(); //wird hier auch empty sein
		List<IssueDTO> issues = new Gson().fromJson(s, listType);
		assertTrue(issues.contains(new IssueDTO(i))); // TODO fix assertion, issues wird leer sein, liegt an Auslesen
														// mit Gson (wie oben)
//		List<IssueDTO> expectedIssues = new ArrayList<>();
//		if (i != null) {
//			IssueDTO iDTO = new IssueDTO(i);
//			expectedIssues.add(iDTO);
//		}
//		assertEquals(issues, expectedIssues);
	}

	@Test
	@WithAnonymousUser
	public void testCloseIssueIfNotLoggedIn() throws Exception {

		// Check if user who are not logged in can't close an issue

		mvc.perform(put("/api/issues" + testIssueId)).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testCloseIssueAsUser() throws Exception {

		// Check if users without administration rights can't close an issue

		mvc.perform(put("/api/issues" + testIssueId)).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
	public void testCloseIssueAsAdmin() throws Exception {

		// CHeck if users with administration rights can close an issue

		mvc.perform(
				put("/api/issues/" + testIssueId).param("iid", testIssueId.toString()).contentType("applications/json"))
				.andExpect(status().isOk());
		assertTrue(i.getCloserId() != null); // TODO gemockter User hat keine ID, die ausgelesen werden kann. Hier
												// Rückgabe mocken?
	}

	@Test
	@WithAnonymousUser
	public void testGetIssueByIDIfNotLoggedIn() throws Exception {

		// Check if who are not logged in can't get an issue by it's ID

		mvc.perform(get("/api/issues/" + testIssueId.toString())).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testGetIssueByIDAsUser() throws Exception {

		// Check if users without administration rights can't get an issue by its ID

		mvc.perform(get("/api/issues" + testIssueId.toString())).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
	public void testGetIssueByIDsAdmin() throws Exception {

		// Check if users with administration rights can get an issue by its ID

		MvcResult result = mvc.perform(get("/api/issues/" + testIssueId.toString()).param("iid", testIssueId.toString())
				.contentType("applications/json")).andExpect(status().isOk()).andReturn();
		IssueDTO issue = new Gson().fromJson(result.getResponse().getContentAsString(), IssueDTO.class);
		assertEquals(issue.getName(), "mts");
		assertEquals(issue.getEmail(), "mattists97@web.de");
		assertEquals(issue.getSubject(), "Test");
		assertEquals(issue.getDescription(), "Hallo. Hallo. Test. Test.");
		assertEquals(issue.getIsClosed(), false);
	}

}
