package de.sopro.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import de.sopro.TestConfig;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfig.class)
public class PictureControllerTest {

	@Autowired
	MockMvc mvc;

//
//	@Test
//	@WithAnonymousUser
	public void testAnalyzeIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to access the analyze method for pictures

		mvc.perform(post("api/pictures")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/login"));
	}

//
//	@Test
//	@WithMockUser(username = "user", roles = { "USER" })
	public void testAnalyzeAsUser() throws Exception {

		// Check if users without administration rights can't access the analyze method
		// for pictures

		mvc.perform(post("api/pictures")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrlPattern("api"));
	}

//
//	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testAnalyzeAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to access the analyze
		// method for pictures

		mvc.perform(post("api/pictures").contentType("image/bmp")).andExpect(status().isOk());
	}

}
